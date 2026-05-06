package com.chealown.csa.UI.Controllers;

import com.chealown.csa.DataBase.Models.*;
import com.chealown.csa.DataBase.Repositories.*;
import com.chealown.csa.Entities.ManageUtil; // Убедитесь, что путь соответствует вашему проекту
//import com.chealown.csa.Entities.SearchUtil;
import com.chealown.csa.Entities.SearchUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.MainApplication;
import com.chealown.csa.UI.Tables.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

public class MainController {

    public AnchorPane tablePane;
    public Label userNameLB;
    public Label roleLB;
    public VBox manualsBox;
    public VBox employeeBox;

    @FXML
    private Button clearFiltersBtn;

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
    private VBox documentsModules;

    @FXML
    private VBox employeesModules;

    @FXML
    private VBox manualsModules;

    @FXML
    private VBox accountingModules;
    @FXML
    private TextField searchTF;
    @FXML
    private Button closeFiltersBtn;
    @FXML
    private AnchorPane filterMenu;
    @FXML
    private AnchorPane filterPane;

    TableView tv;
    TableModule module;

    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    FiltersController controller;

    @FXML
    private void initialize() throws SQLException {
        userNameLB.setText(
                SecurityUtil.decryptSafe(StaticObjects.getCurrentUser().getSecondName(), key) + " " +
                        SecurityUtil.decryptSafe(StaticObjects.getCurrentUser().getFirstName(), key).charAt(0) + ". " +
                        SecurityUtil.decryptSafe(StaticObjects.getCurrentUser().getPatronymic(), key).charAt(0) + ". "
        );

        roleLB.setText(StaticObjects.getCurrentUser().getPost());

        StaticObjects.setSelectedObject(null);

        tablePane.getChildren().clear();
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

            switch (Objects.requireNonNull(StaticObjects.getLastPage().getText())) {
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
                case "Организации":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/OrganizationFiltersContent-view.fxml"));
                    try {
                        Node node = loader.load();
                        filterPane.getChildren().add(node);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller = loader.getController();
                    break;
                case "Сотрудники":
                    loader = new FXMLLoader(MainApplication.class.getResource("FXML/Filters/EmployeeFiltersContent-view.fxml"));
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
            try {
                applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        setTreeModules();
        loadData(StaticObjects.getLastPage());


        // ВЫХОД ИЗ АККАУНТА
        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Авторизация", "AuthorizationPage-view");
                StaticObjects.setCurrentUser(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setTreeModules() {
        Hyperlink[] accounting = new Hyperlink[]{
                new Hyperlink("Дети"),
                new Hyperlink("Взаимодействия с внешними службами"),
                new Hyperlink("Жилищные права"),
                new Hyperlink("Очередь на получение жилья"),
                new Hyperlink("Социальный мониторинг"),
                new Hyperlink("Социальный паспорт")
        };

        Hyperlink[] documents = new Hyperlink[]{
                new Hyperlink("Документы"),
                new Hyperlink("Шаблоны документов")
        };

        Hyperlink[] manuals = new Hyperlink[]{
                new Hyperlink("Группы здоровья"),
                new Hyperlink("Должности"),
                new Hyperlink("Организации"),
                new Hyperlink("Семейные положения"),
                new Hyperlink("Статусы"),
                new Hyperlink("Типы взаимодействий"),
                new Hyperlink("Типы организаций"),
                new Hyperlink("Учебные группы")
        };

        Hyperlink[] employees = new Hyperlink[]{
                new Hyperlink("Сотрудники"),
                new Hyperlink("Пользователи")
        };

        accountingModules.getChildren().addAll(accounting);
        documentsModules.getChildren().add(documents[0]);

        if (StaticObjects.getCurrentUser().getPost().equals("Администратор ПО")) {
            documentsModules.getChildren().add(documents[1]);
            manualsBox.setVisible(true);
            employeeBox.setVisible(true);
            manualsModules.getChildren().addAll(manuals);
            employeesModules.getChildren().addAll(employees);
        }

        Hyperlink[] allLinks = Stream.of(accounting, documents, manuals, employees)
                .flatMap(Arrays::stream)
                .toArray(Hyperlink[]::new);


        for (Hyperlink hl : allLinks) {
            setBaseViewLinks(hl);
            hl.setOnAction(actionEvent -> {
                try {
                    changeTable(actionEvent, allLinks);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            });

            if (StaticObjects.isFirstRun()) {
                selectTableModule(accounting[0]);
                StaticObjects.setLastPage(accounting[0]);
                StaticObjects.setFirstRun(false);
            } else {
                if (StaticObjects.getLastPage().getText().equals(hl.getText())) {
                    selectTableModule(hl);
                }


            }
        }
    }

    private void selectTableModule(Hyperlink hl) {
        hl.setOpacity(1);
        hl.setStyle("-fx-font-size:20; -fx-text-fill: #6b7ee9; -fx-border-color:transparent;");
    }

    // ИЗНАЧАЛЬНЫЙ ВИД МЕНЮ
    private void setBaseView() {
        filterMenu.setVisible(false);
        filterMenu.setDisable(true);
        burgerMenu.setVisible(false);
        burgerMenu.setDisable(true);
        darkPane.setVisible(false);
        darkPane.setDisable(true);
    }

    private void loadData(Hyperlink table) throws SQLException {
        tablePane.getChildren().clear();

        Node node = null;
        TableModule module = null;
        switch (table.getText()) {
            case "Дети":
                module = new ChildrenTableModule();
                showFilterBtn();
                break;
            case "Социальный паспорт":
                module = new SPTableModule();
                showFilterBtn();
                break;
            case "Социальный мониторинг":
                module = new SMTableModule();
                showFilterBtn();
                break;
            case "Жилищные права":
                module = new HRTableModule();
                showFilterBtn();
                break;
            case "Очередь на получение жилья":
                module = new WLFHTableModule();
                showFilterBtn();
                break;
            case "Взаимодействия с внешними службами":
                module = new InteractionTableModule();
                showFilterBtn();
                break;
            case "Документы":
                module = new DocumentTableModule();
                hideFilterBtn();
                break;
            case "Шаблоны документов":
                module = new TemplateTableModule();
                hideFilterBtn();
                break;
            case "Группы здоровья":
                module = new HGTableModule();
                hideFilterBtn();
                break;
            case "Должности":
                module = new PostTableModule();
                hideFilterBtn();
                break;
            case "Организации":
                module = new OrganizationTableModule();
                showFilterBtn();
                break;
            case "Семейные положения":
                module = new FSTableModule();
                hideFilterBtn();
                break;
            case "Статусы":
                module = new StatusTableModule();
                hideFilterBtn();
                break;
            case "Типы взаимодействий":
                module = new ITTableModule();
                hideFilterBtn();
                break;
            case "Типы организаций":
                module = new OTTableModule();
                hideFilterBtn();
                break;
            case "Учебные группы":
                module = new EGTableModule();
                hideFilterBtn();
                break;
            case "Сотрудники":
                module = new EmployeeTableModule();
                showFilterBtn();
                break;
            case "Пользователи":
                module = new UserTableModule();
                hideFilterBtn();
                break;
        }
        node = module.getTableView();
        // добавление таблицы на форму
        tablePane.getChildren().add(node);

        // установка ссылки на текущую таблицу
        tv = module.getTableView();

        // учтановка модуля для текущей таблицы
        this.module = module;

        setContextMenu();

        // установка данных и названия текщей таблицы
        StaticObjects.setLastPage(table);
    }

    private void hideFilterBtn() {
        filterBtn.setManaged(false);
        filterBtn.setVisible(false);
    }

    private void showFilterBtn() {
        filterBtn.setManaged(true);
        filterBtn.setVisible(true);
    }

    private void setContextMenu() {
        tv.setRowFactory(tv -> {
            TableRow<Map<String, Object>> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-font-size: 20px");
            MenuItem editItem = new MenuItem("✏️ Изменить");
            MenuItem deleteItem = new MenuItem("🗑 Удалить");
            editItem.setOnAction(e -> {
                Object item = row.getItem();
                if (item != null && !row.isEmpty()) {
                    StaticObjects.setSelectedObject(item);
                    try {
                        callAddEditForm();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            deleteItem.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены, что хотите удалить?");
                alert.setTitle("Удаление записи");
                Optional<ButtonType> buttonType = alert.showAndWait();

                boolean isAdmin = StaticObjects.getCurrentUser().getPost().equals("Администратор ПО");

                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    Object item = row.getItem();
                    if (item != null && !row.isEmpty()) {
                        switch (StaticObjects.getLastPage().getText()) {
                            case "Дети":
                                ChildrenRepository.deleteRecord((Children) item, isAdmin);
                                break;
                            case "Социальный паспорт":
                                SocialPassportRepository.deleteRecord((SocialPassport) item, isAdmin);
                                break;
                            case "Социальный мониторинг":
                                SocialMonitoringRepository.deleteRecord((SocialMonitoring) item, isAdmin);
                                break;
                            case "Жилищные права":
                                HousingRightsRepository.deleteRecord((HousingRights) item, isAdmin);
                                break;
                            case "Очередь на получение жилья":
                                WLFHRepository.deleteRecord((WaitingListForHousing) item, isAdmin);
                                break;
                            case "Взаимодействия с внешними службами":
                                InteractionRepository.deleteRecord((Interaction) item, isAdmin);
                                break;
                            case "Документы":
                                DocumentRepository.deleteRecord((Document) item, isAdmin);
                                break;
                            case "Шаблоны документов":
                                TemplateRepository.delete((TemplateDocument) item);
                            case "Группы здоровья":
                                HealthGroupRepository.delete((HealthGroup) item);
                                break;
                            case "Должности":
                                PostRepository.delete((Post) item);
                                break;
                            case "Организации":
                                OrganizationRepository.delete((Organization) item);
                                break;
                            case "Семейные положения":
                                FamilySituationRepository.delete((FamilySituation) item);
                                break;
                            case "Статусы":
                                StatusRepository.delete((Status) item);
                                break;
                            case "Типы взаимодействий":
                                InteractionTypeRepository.delete((InteractionType) item);
                                break;
                            case "Типы организаций":
                                OrganizationTypeRepository.delete((OrganizationType) item);
                                break;
                            case "Учебные группы":
                                EducationGroupRepository.delete((EducationGroup) item);
                                break;
                            case "Сотрудники":
                                EmployeeRepository.delete((Employee) item);
                                break;
                            case "Пользователи":
                                UserRepository.delete((User) item);
                                break;
                            case null:
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + StaticObjects.getLastPage());
                        }
                    }
                }

                try {
                    loadData(StaticObjects.getLastPage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
//
            contextMenu.getItems().addAll(editItem, deleteItem);

            row.emptyProperty().addListener((obs, wasEmpty, isEmpty) -> {
                if (isEmpty)
                    row.setContextMenu(null);
                else
                    row.setContextMenu(contextMenu);
            });
//
            return row;
        });
    }

    public void applySearchAndFilters() throws SQLException {
        String currentTableName = Objects.requireNonNull(StaticObjects.getLastPage().getText());

        String[] searchColumns = switch (currentTableName) {
            case "Дети" -> new String[]{"Фамилия", "Имя", "Отчество", "СНИЛС"};
            case "Социальный паспорт" -> new String[]{"Фамилия", "Имя", "Отчество"};
            case "Социальный мониторинг" ->
                    new String[]{"Фамилия", "Имя", "Отчество", "Старое значение", "Новое значение"};
            case "Жилищные права" -> new String[]{"Фамилия", "Имя", "Отчество", "Город", "Улица", "Здание"};
            case "Очередь на получение жилья" -> new String[]{"Фамилия", "Имя", "Отчество", "Текущий шаг"};
            case "Взаимодействия с внешними службами" ->
                    new String[]{"Фамилия", "Имя", "Отчество", "Название организации", "Результат"};
            case "Документы" -> new String[]{"Название документа"};
            case "Шаблоны документов" -> new String[]{"Название шаблона"};
            case "Группы здоровья" -> new String[]{"Название группы здоровья"};
            case "Должности" -> new String[]{"Название должности"};
            case "Организации" ->
                    new String[]{"Название организации", "Город", "Улица", "Здание", "Электронная почта", "Номер телефона"};
            case "Семейные положения" -> new String[]{"Название семейного положения"};
            case "Статусы" -> new String[]{"Название статуса"};
            case "Типы взаимодействий" -> new String[]{"Название типа взаимодействия"};
            case "Типы организаций" -> new String[]{"Название типа организации"};
            case "Учебные группы" ->
                    new String[]{"Название группы", "Фамилия куратора", "Имя куратора", "Отчество куратора"};
            case "Сотрудники" -> new String[]{"Фамилия", "Имя", "Отчество", "Электронная почта", "Номер телефона"};
            case "Пользователи" -> new String[]{"Логин", "Фамилия", "Имя", "Отчество"};
            default -> new String[0];
        };

        String[] dateColumns = switch (currentTableName) {
            case "Дети" -> new String[]{"Дата рождения"};
            case "Социальный паспорт" -> new String[]{"Дата создания"};
            case "Социальный мониторинг" -> new String[]{"Дата фиксации"};
            case "Жилищные права" -> new String[]{"Дата регистрации"};
            case "Очередь на получение жилья" -> new String[]{"Дата постановки в очередь", "Ожидаемая дата выдачи"};
            case "Взаимодействия с внешними службами" -> new String[]{"Дата взаимодействия"};
            default -> new String[0];
        };

        String[] filterColumns = switch (currentTableName) {
            case "Дети" -> new String[]{"Пол", "Учебная группа", "Статус"};
            case "Социальный паспорт" ->
                    new String[]{"Образование", "Группа здоровья", "Семейное положение", "Инвалидность"};
            case "Социальный мониторинг" -> new String[]{"Тип мониторинга"};
            case "Жилищные права" -> new String[]{"Наличие жилья", "Форма собственности", "Город"};
            case "Взаимодействия с внешними службами" -> new String[]{"Тип взаимодействия"};
            case "Организации" -> new String[]{"Город", "Тип организации"};
            case "Сотрудники" -> new String[]{"Должность"};
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
        // Очистка таблицы перед обновлением
        tv.getItems().clear();

        List<Object> filteredData = SearchUtil.searchData(
                StaticObjects.getListTableData(),
                StaticObjects.getMapTableData(),
                searchTF.getText().toLowerCase(),
                searchColumns,
                filterData,
                filterColumns,
                dateColumns,
                startDate,
                endDate
        );
//         Добавляем отфильтрованные данные в таблицу
        module.loadData(filteredData);
    }

    // ПЕРЕКЛЮЧЕНИЕ МЕЖДУ МОДУЛЯМИ
    private void changeTable(ActionEvent actionEvent, Hyperlink[] allLinks) throws IOException, SQLException {
        searchTF.setText("");

        if (actionEvent.getSource() instanceof Hyperlink link) {
            for (Hyperlink h : allLinks) {
                setBaseViewLinks(h);
            }
            link.setOpacity(1);
            link.setStyle("-fx-font-size:20; -fx-text-fill: #6b7ee9; -fx-border-color:transparent");

            loadData(link);
        }
    }

    private void setBaseViewLinks(Hyperlink hl) {
        hl.setOpacity(0.5);
        hl.setUnderline(false);
        hl.setStyle("-fx-font-size:20; -fx-text-fill:#242424; -fx-border-color:transparent");
        hl.setWrapText(true);
//        hl.setMaxWidth(();
    }

    @FXML
    private void addNewData(ActionEvent actionEvent) throws IOException {
        callAddEditForm();
    }

    private void callAddEditForm() throws IOException {
        switch (StaticObjects.getLastPage().getText()) {
            case "Дети":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditChildrenPage-view");
                break;
            case "Социальный паспорт":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditSPPage-view");
                break;
            case "Социальный мониторинг":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditSMPage-view");
                break;
            case "Жилищные права":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditHRPage-view");
                break;
            case "Очередь на получение жилья":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditWLFHPage-view");
                break;
            case "Взаимодействия с внешними службами":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditInteractionPage-view");
                break;
            case "Документы":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditDocumentPage-view");
                break;
            case "Шаблоны документов":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditTemplateDocPage-view");
                break;
            case "Группы здоровья":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditHGPage-view");
                break;
            case "Должности":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditPostPage-view");
                break;
            case "Организации":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditOrganizationPage-view");
                break;
            case "Семейные положения":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditStatusPage-view");
                break;
            case "Статусы":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditStatusPage-view");
                break;
            case "Типы взаимодействий":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditITPage-view");
                break;
            case "Типы организаций":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditOPPage-view");
                break;
            case "Учебные группы":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditEGPage-view");
                break;
            case "Сотрудники":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditEmployeePage-view");
                break;
            case "Пользователи":
                ManageUtil.switchPage("Добавление записи", "AddEdit/AddEditUserPage-view");
                break;
            case null:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StaticObjects.getLastPage());
        }
    }
}