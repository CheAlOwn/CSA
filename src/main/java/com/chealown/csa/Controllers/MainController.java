package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.*;
import com.chealown.csa.DataBase.Repositories.*;
import com.chealown.csa.Entities.ManageUtil; // Убедитесь, что путь соответствует вашему проекту
import com.chealown.csa.Entities.SearchUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.MainApplication;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Security;
import java.sql.ResultSet;
import java.util.*;

public class MainController {

    @FXML
    private Button clearFiltersBtn;

    @FXML
    private Hyperlink interactionHL;

    @FXML
    private Hyperlink childHL;
    @FXML
    private Hyperlink spHL;
    @FXML
    private Hyperlink smHL;
    @FXML
    private Hyperlink hrHL;
    @FXML
    private Hyperlink wlfhHL;
    @FXML
    private Button closeMenuBtn;
    @FXML
    private Button addBtn;
    @FXML
    private AnchorPane burgerMenu;
    @FXML
    private AnchorPane darkPane;
    @FXML
    private Hyperlink exitBtn;
    @FXML
    private Button filterBtn;
    @FXML
    private Button menuBtn;
    @FXML
    private VBox modulesList;
    @FXML
    private TextField searchTF;
    @FXML
    private Button sortBtn;
    @FXML
    private TableView<Map<String, Object>> tableView;
    @FXML
    private Button closeFiltersBtn;
    @FXML
    private AnchorPane filterMenu;
    @FXML
    private AnchorPane filterPane;

    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    FiltersController controller;

    @FXML
    private void initialize() {
        burgerMenu.setLayoutX(-470);
        filterMenu.setLayoutX(1920);
        setBaseView();

        menuBtn.setOnAction(actionEvent -> {
            burgerMenu.setVisible(true);
            burgerMenu.setDisable(false);
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(0);
            darkPane.setVisible(true);
            darkPane.setDisable(false);
            slide.play();
        });


        filterBtn.setOnAction(actionEvent -> {
            filterMenu.setVisible(true);
            filterMenu.setDisable(false);
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(1450);
            darkPane.setVisible(true);
            darkPane.setDisable(false);
            slide.play();

            filterPane.getChildren().clear();

            FXMLLoader loader = null;

            switch (Objects.requireNonNull(StaticObjects.getCurrentTableName())) {
                case "Дети":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/ChildrenFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Социальный паспорт":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/SPFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Социальный мониторинг":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/SMFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Жилищные права":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/HRFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Очередь на получение жилья":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/WLFHFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Взаимодействия с внешними службами":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/InteractionFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                default:
            }
        });

        closeMenuBtn.setOnAction(actionEvent -> {
            setBaseView();
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(-470);
            slide.play();
        });

        closeFiltersBtn.setOnAction(actionEvent -> {
            setBaseView();
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(1920);
            slide.play();
        });

        clearFiltersBtn.setOnAction(actionEvent -> {
            if (controller != null)
                controller.clearFilters();
        });

        searchTF.textProperty().addListener(lst -> {
            applySearchAndFilters();
        });


        tableView.setStyle("-fx-font-size: 20px");
        tableView.setRowFactory(tv -> {
            TableRow<Map<String, Object>> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-font-size: 20px");
            MenuItem editItem = new MenuItem("✏️ Изменить");
            MenuItem deleteItem = new MenuItem("🗑 Удалить");
            editItem.setOnAction(e -> {
                Map<String, Object> item = row.getItem();
                if (item != null && !row.isEmpty()) {
                    switch (StaticObjects.getCurrentTableName()) {
                        case "Дети":
                            StaticObjects.setChildren(new Children(
                                    item.get("ID"),
                                    item.get("Фамилия"),
                                    item.get("Имя"),
                                    item.get("Отчество"),
                                    item.get("Дата рождения"),
                                    item.get("Пол"),
                                    item.get("СНИЛС"),
                                    item.get("Номер паспорта"),
                                    item.get("Серия паспорта"),
                                    item.get("Дата регистрации"),
                                    item.get("Учебная группа"),
                                    item.get("Статус")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditChildrenPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Социальный паспорт":
                            StaticObjects.setSocialPassport(new SocialPassport(
                                    item.get("ID"),
                                    item.get("Фамилия"),
                                    item.get("Имя"),
                                    item.get("Отчество"),
                                    item.get("Уровень образования"),
                                    item.get("Группа здоровья"),
                                    item.get("Семейное положение"),
                                    item.get("Инвалидность"),
                                    item.get("Дата создания"),
                                    item.get("ID ребенка")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditSPPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Социальный мониторинг":
                            StaticObjects.setSocialMonitoring(new SocialMonitoring(
                                    item.get("ID"),
                                    item.get("Фамилия ребенка"),
                                    item.get("Имя ребенка"),
                                    item.get("Отчество ребенка"),
                                    item.get("Дата фиксации"),
                                    item.get("Тип мониторинга"),
                                    item.get("Старое значение"),
                                    item.get("Новое значение"),
                                    item.get("Причина изменения"),
                                    item.get("ID ребенка")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditSMPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Жилищные права":
                            StaticObjects.setHousingRights(new HousingRights(
                                    item.get("ID"),
                                    item.get("Фамилия"),
                                    item.get("Имя"),
                                    item.get("Отчество"),
                                    item.get("Наличие жилья"),
                                    item.get("Форма собственности"),
                                    item.get("Дата регистрации"),
                                    item.get("Город"),
                                    item.get("Улица"),
                                    item.get("Здание"),
                                    item.get("ID ребенка")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditHRPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Очередь на получение жилья":
                            StaticObjects.setListForHousing(new WaitingListForHousing(
                                    item.get("ID"),
                                    item.get("Фамилия"),
                                    item.get("Имя"),
                                    item.get("Отчество"),
                                    item.get("Номер в очереди"),
                                    item.get("Дата постановки в очередь"),
                                    item.get("Ожидаемая дата выдачи"),
                                    item.get("Текущий шаг"),
                                    item.get("ID ребенка")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditWLFHPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Взаимодействия с внешними службами":
                            StaticObjects.setInteraction(new Interaction(
                                    item.get("ID"),
                                    item.get("Название организации"),
                                    item.get("Фамилия"),
                                    item.get("Имя"),
                                    item.get("Отчество"),
                                    item.get("Дата взаимодействия"),
                                    item.get("Тип взаимодействия"),
                                    item.get("Результат взаимодействия"),
                                    item.get("ID ребенка")
                            ));
                            try {
                                ManageUtil.switchPage("Редактирование записи", "AddEditInteractionPage-view");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case null:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + StaticObjects.getCurrentTableName());
                    }

                    System.out.println(item);
                }
            });

            deleteItem.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены, что хотите удалить?");
                alert.setTitle("Удаление записи");
                Optional<ButtonType> buttonType = alert.showAndWait();

                String query = "";
                String[] displayColumns = {};
                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    Map<String, Object> item = row.getItem();
                    if (item != null && !row.isEmpty()) {
                        switch (StaticObjects.getCurrentTableName()) {
                            case "Дети":
                                Children child = new Children();
                                child.setId(Integer.parseInt(item.get("ID").toString()));
                                ChildrenRepository.archive(child);
                                query = ChildrenRepository.getQUERY();
                                displayColumns = ChildrenRepository.getDisplayColumns();
                                break;
                            case "Социальный паспорт":
                                SocialPassport sp = new SocialPassport();
                                sp.setId(Integer.parseInt(item.get("ID").toString()));
                                SocialPassportRepository.archive(sp);
                                query = SocialPassportRepository.getQUERY();
                                displayColumns = SocialPassportRepository.getDisplayColumns();
                                break;
                            case "Социальный мониторинг":
                                SocialMonitoring sm = new SocialMonitoring();
                                sm.setId(Integer.parseInt(item.get("ID").toString()));
                                SocialMonitoringRepository.archive(sm);
                                query = SocialMonitoringRepository.getQUERY();
                                displayColumns = SocialMonitoringRepository.getDisplayColumns();
                                break;
                            case "Жилищные права":
                                HousingRights hr = new HousingRights();
                                hr.setId(Integer.parseInt(item.get("ID").toString()));
                                HousingRightsRepository.archive(hr);
                                query = HousingRightsRepository.getQUERY();
                                displayColumns = HousingRightsRepository.getDisplayColumns();
                                break;
                            case "Очередь на получение жилья":
                                WaitingListForHousing wlfh = new WaitingListForHousing();
                                wlfh.setId(Integer.parseInt(item.get("ID").toString()));
                                WLFHRepository.archive(wlfh);
                                query = WLFHRepository.getQUERY();
                                displayColumns = WLFHRepository.getDisplayColumns();
                                break;
                            case "Взаимодействия с внешними службами":
                                Interaction interaction = new Interaction();
                                interaction.setId(Integer.parseInt(item.get("ID").toString()));
                                InteractionRepository.archive(interaction);
                                query = InteractionRepository.getQUERY();
                                displayColumns = InteractionRepository.getDisplayColumns();
                                break;
                            case null:
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + StaticObjects.getCurrentTableName());
                        }
                    }
                }

                loadData(StaticObjects.getCurrentTableName(), query, displayColumns);
            });

            contextMenu.getItems().addAll(editItem, deleteItem);

            row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
                if (isEmpty)
                    row.setContextMenu(null);
                else
                    row.setContextMenu(contextMenu);
            });

            return row;
        });


        loadData("Дети", ChildrenRepository.getQUERY(), ChildrenRepository.getDisplayColumns());

        childHL.setOpacity(1);
        exitBtn.setOnAction(actionEvent ->

        {
            try {
                ManageUtil.switchPage("Авторизация", "AuthorizationPage-view");
                StaticObjects.setCurrentUser(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //
    //
    //
    //
    //
    // TODO: убрать дату регистрации отовсюду
//
//
//
//

    public void applySearchAndFilters() {
        String[] searchColumns = switch (Objects.requireNonNull(StaticObjects.getCurrentTableName())) {
            case "Дети" -> new String[]{"Фамилия", "Имя", "Отчество", "СНИЛС"};
            case "Социальный паспорт" -> new String[]{"Фамилия", "Имя", "Отчество"};
            case "Социальный мониторинг" ->
                    new String[]{"Фамилия ребенка", "Имя ребенка", "Отчество ребенка", "Старое значение", "Новое значение"};
            case "Жилищные права" -> new String[]{"Фамилия", "Имя", "Отчество", "Город", "Улица", "Здание"};
            case "Очередь на получение жилья" -> new String[]{"Фамилия", "Имя", "Отчество", "Текущий шаг"};
            case "Взаимодействия с внешними службами" ->
                    new String[]{"Фамилия", "Имя", "Отчество", "Название организации", "Результат взаимодействия"};
            default -> new String[0];
        };

        String[] dateColumns = switch (Objects.requireNonNull(StaticObjects.getCurrentTableName())) {
            case "Дети" -> new String[]{"Дата рождения"};
            case "Социальный паспорт" -> new String[]{"Дата создания"};
            case "Социальный мониторинг" -> new String[]{"Дата фиксации"};
            case "Жилищные права" -> new String[]{"Дата регистрации"};
            case "Очередь на получение жилья" -> new String[]{"Дата постановки в очередь", "Ожидаемая дата выдачи"};
            case "Взаимодействия с внешними службами" -> new String[]{"Дата взаимодействия"};
            default -> new String[0];
        };

        String[] filterColumns = switch (Objects.requireNonNull(StaticObjects.getCurrentTableName())) {
            case "Дети" -> new String[]{"Пол", "Учебная группа", "Статус"};
            case "Социальный паспорт" ->
                    new String[]{"Уровень образования", "Группа здоровья", "Семейное положение", "Инвалидность"};
            case "Социальный мониторинг" -> new String[]{"Тип мониторинга"};
            case "Жилищные права" -> new String[]{"Наличие жилья", "Форма собственности", "Город"};
            case "Взаимодействия с внешними службами" -> new String[]{"Тип взаимодействия"};
            default -> new String[0];
        };
        String[] filterData = null;
        String[] startDate = null;
        String[] endDate = null;

        if (controller != null) {
            filterData = controller.getFilterDataList();
            startDate = controller.getStartDateList();
            endDate = controller.getEndDateList();
        }

        tableView.getItems().clear();
        tableView.getItems().addAll(
                SearchUtil.searchData(
                        Objects.requireNonNull(StaticObjects.getCurrentTableData()),
                        searchTF.getText().toLowerCase(),
                        searchColumns,
                        filterData,
                        filterColumns,
                        dateColumns,
                        startDate,
                        endDate
                ));
    }

    private void setBaseView() {
        filterMenu.setVisible(false);
        filterMenu.setDisable(true);
        burgerMenu.setVisible(false);
        burgerMenu.setDisable(true);
        darkPane.setVisible(false);
        darkPane.setDisable(true);
    }

    @FXML
    private void changeTable(ActionEvent actionEvent) {
        Hyperlink[] hyperlinks = {
                childHL,
                spHL,
                smHL,
                hrHL,
                wlfhHL,
                interactionHL
        };

        if (actionEvent.getSource() instanceof Hyperlink link) {
            String tableTitle = link.getText();
            for (Hyperlink h : hyperlinks) {
                h.setOpacity(0.5);
            }
            link.setOpacity(1);

            String sql = switch (tableTitle) {
                case "Дети" -> ChildrenRepository.getQUERY();
                case "Социальный паспорт" -> SocialPassportRepository.getQUERY();
                case "Социальный мониторинг" -> SocialMonitoringRepository.getQUERY();
                case "Жилищные права" -> HousingRightsRepository.getQUERY();
                case "Очередь на получение жилья" -> WLFHRepository.getQUERY();
                case "Взаимодействия с внешними службами" -> InteractionRepository.getQUERY();
                default -> null;
            };

            if (sql != null) {
                String[] displayColumns = switch (tableTitle) {
                    case "Дети" -> ChildrenRepository.getDisplayColumns();
                    case "Социальный паспорт" -> SocialPassportRepository.getDisplayColumns();
                    case "Социальный мониторинг" -> SocialMonitoringRepository.getDisplayColumns();
                    case "Жилищные права" -> HousingRightsRepository.getDisplayColumns();
                    case "Очередь на получение жилья" -> WLFHRepository.getDisplayColumns();
                    case "Взаимодействия с внешними службами" -> InteractionRepository.getDisplayColumns();
                    default -> new String[0];
                };

                loadData(tableTitle, sql, displayColumns);
            }
        }
    }

    private void loadData(String tableName, String sql, String[] columns) {
        tableView.setDisable(true);
        tableView.setPlaceholder(new Label("Загрузка..."));

        Task<ObservableList<Map<String, Object>>> task = new Task<>() {
            // Map = колонка + значение
            @Override
            protected ObservableList<Map<String, Object>> call() throws Exception {
                List<Map<String, Object>> data = new ArrayList<>();

                try (ResultSet rs = DBConnector.query(sql)) {
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        for (int i = 0; i < columns.length; i++) {
                            Object obj = rs.getObject(i + 1);

                            if (obj != null && !(obj.toString()).matches("[0-9.]*") &&
                                    !columns[i].equals("Тип мониторинга") &&
                                    !columns[i].equals("Наличие жилья") &&
                                    !columns[i].equals("Форма собственности") &&
                                    !columns[i].equals("Текущий шаг") &&
                                    !columns[i].equals("Название организации") &&
                                    !columns[i].equals("Тип взаимодействия") &&
                                    !columns[i].equals("Результат взаимодействия")
                            ) {
                                obj = SecurityUtil.decryptSafe((String) obj, key);
                            }

                            row.put(columns[i], obj);
                        }
                        data.add(row);
                    }
                }
                StaticObjects.setCurrentTable(tableName, data);
                return FXCollections.observableArrayList(data);
            }
        };

        task.setOnSucceeded(e -> {
            tableView.getColumns().clear();

            for (String colName : columns) {
                TableColumn<Map<String, Object>, String> col = new TableColumn<>(colName);
                col.setCellValueFactory(cell -> {
                    Object value = cell.getValue().get(colName);
                    return new SimpleStringProperty(value != null ? value.toString() : "");
                });
                tableView.getColumns().add(col);
            }
            tableView.setItems(task.getValue());
            tableView.setDisable(false);
            tableView.setPlaceholder(new Label("Пусто"));
        });
        task.setOnFailed(e -> {
            tableView.setDisable(false);
            tableView.setPlaceholder(new Label("Ошибка!"));
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    @FXML
    private void addNewData(ActionEvent actionEvent) throws IOException {
        switch (StaticObjects.getCurrentTableName()) {
            case "Дети":
                ManageUtil.switchPage("Добавление записи", "AddEditChildrenPage-view");
                break;
            case "Социальный паспорт":
                ManageUtil.switchPage("Добавление записи", "AddEditSPPage-view");
                break;
            case "Социальный мониторинг":
                ManageUtil.switchPage("Добавление записи", "AddEditSMPage-view");
                break;
            case "Жилищные права":
                ManageUtil.switchPage("Добавление записи", "AddEditHRPage-view");
                break;
            case "Очередь на получение жилья":
                ManageUtil.switchPage("Добавление записи", "AddEditWLFHPage-view");
                break;
            case "Взаимодействия с внешними службами":
                ManageUtil.switchPage("Добавление записи", "AddEditInteractionPage-view");
                break;
            case null:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StaticObjects.getCurrentTableName());
        }
    }
}