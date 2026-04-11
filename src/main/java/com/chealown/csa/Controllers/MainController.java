package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.*;
import com.chealown.csa.DataBase.Repositories.*;
import com.chealown.csa.Entities.ManageUtil; // Убедитесь, что путь соответствует вашему проекту
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    @FXML
    private void initialize() {
        // анимация бургер меню
        burgerMenu.setLayoutX(-470);
        setBaseView();

        // проигрывание анимации бургер меню
        menuBtn.setOnAction(actionEvent -> {
            burgerMenu.setVisible(true);
            burgerMenu.setDisable(false);
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(0);

            darkPane.setVisible(true);
            darkPane.setDisable(false);

            slide.play();
        });

        // проигрывание анимации бургер меню
        closeMenuBtn.setOnAction(actionEvent -> {
            setBaseView();
            TranslateTransition slide = new TranslateTransition();
            slide.setToX(-470);


            slide.play();
        });

        tableView.setStyle("-fx-font-size: 20px");
        tableView.setRowFactory(tv -> {
            TableRow<Map<String, Object>> row = new TableRow<>();

            // Создаём контекстное меню
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setStyle("-fx-font-size: 20px");

            MenuItem editItem = new MenuItem("✏️ Изменить");
            MenuItem deleteItem = new MenuItem("🗑 Удалить");

            // Обработчики
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
                                    item.get("Фамилия пользователя"),
                                    item.get("Имя пользователя"),
                                    item.get("Отчество пользователя"),
                                    item.get("ID ребенка"),
                                    item.get("ID пользователя")
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
                                    item.get("ID ребенка")  // добавлено idChildren
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
                        case null:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + StaticObjects.getCurrentTableName());
                    }

                    System.out.println(item);
                }
            });

            deleteItem.setOnAction(e -> {
                Object item = row.getItem();
                if (item != null && !row.isEmpty()) {
                    // Автоматически обновит UI, если tableView.getItems() -> ObservableList
                    System.out.println("Удалена строка: ");
                    // todo: заглушка
                }
            });

            contextMenu.getItems().addAll(editItem, deleteItem);

            // Привязываем меню к строке
            row.setContextMenu(contextMenu);

            return row;
        });


        loadData("Дети", """
                SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
                               g.gender_name, c.snils, c.passport_series, c.passport_number,
                               c.registration_date, eg.group_name, s.status_name
                        FROM children c
                INNER JOIN gender g ON c.gender = g.id
                INNER JOIN education_group eg ON c.id_education_group = eg.id
                INNER JOIN status s ON c.status = s.id
                """, new String[]{"ID", "Фамилия", "Имя", "Отчество", "Дата рождения", "Пол", "СНИЛС", "Серия паспорта", "Номер паспорта", "Дата регистрации", "Учебная группа", "Статус"});


        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Авторизация", "AuthorizationPage-view");
                StaticObjects.setCurrentUser(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setBaseView() {
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
                wlfhHL
        };

        if (actionEvent.getSource() instanceof Hyperlink link) {
            String tableTitle = link.getText();

            for (Hyperlink h: hyperlinks) {
                h.setOpacity(0.5);

            }
            link.setOpacity(1);


            // Сделать разграничение по ролям
            // SQL-запросы. Добавлены алиасы для избежания конфликтов имён колонок (например, second_name в children и employee)
            String sql = switch (tableTitle) {
                case "Дети" -> """
                        SELECT c.id, c.second_name, c.first_name, c.patronymic, c.birthdate,
                               g.gender_name, c.snils, c.passport_series, c.passport_number,
                               c.registration_date, eg.group_name, s.status_name
                        FROM children c
                        INNER JOIN gender g ON c.gender = g.id
                        INNER JOIN education_group eg ON c.id_education_group = eg.id
                        INNER JOIN status s ON c.status = s.id
                        """;
                case "Социальный паспорт" -> """
                        SELECT s.id_passport, c.second_name, c.first_name, c.patronymic, c.id,
                               el.education_name, hg.group_name, t.situation_name,
                               s.having_a_disability, s.date_create
                        FROM social_passport s
                        INNER JOIN children c ON s.id_children = c.id
                        INNER JOIN education_level el ON s.id_education = el.id
                        INNER JOIN health_group hg ON s.id_health_group = hg.id
                        INNER JOIN family_situation t ON s.id_family_situation = t.id
                        """;
                case "Социальный мониторинг" -> """
                        SELECT s.id, c.second_name AS child_surname, c.first_name AS child_first, c.patronymic AS child_patronymic, c.id,
                               s.date_of_fixation, mt.monitoring_name, s.old_value,
                               s.new_value, s.change_reason,
                               e.second_name AS emp_surname, e.first_name AS emp_first, e.patronymic AS emp_patronymic, u.id
                        FROM social_monitoring s
                        INNER JOIN children c ON s.id_children = c.id
                        INNER JOIN monitoring_type mt ON s.id_monitoring_type = mt.id
                        INNER JOIN "user" u ON s.id_user = u.id
                        INNER JOIN employee e ON u.id_employee = e.id
                        """;
                case "Жилищные права" -> """
                        SELECT h.id, c.second_name, c.first_name, c.patronymic, c.id,
                               h.availability_of_housing, form_name,
                               h.registration_date, h.city, h.street, h.build
                        FROM housing_rights h
                        INNER JOIN children c ON h.id_children = c.id
                        INNER JOIN ownership_form o ON h.form_of_ownership=o.id
                        """;
                case "Очередь на получение жилья" -> """
                        SELECT w.id, c.second_name, c.first_name, c.patronymic, c.id,
                               w.number_in_the_queue, w.date_added,
                               w.expected_date_of_issue, w.current_step
                        FROM waiting_list_for_housing w
                        INNER JOIN children c ON w.id_children = c.id
                        """;
                default -> null;
            };

            if (sql != null) {
                // Соответствие позиций в SELECT и отображаемых заголовков
                String[] displayColumns = switch (tableTitle) {
                    case "Дети" -> new String[]{
                            "ID", "Фамилия", "Имя", "Отчество",
                            "Дата рождения", "Пол", "СНИЛС", "Серия паспорта",
                            "Номер паспорта", "Дата регистрации", "Учебная группа", "Статус"
                    };
                    case "Социальный паспорт" -> new String[]{
                            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка", "Уровень образования",
                            "Группа здоровья", "Семейное положение", "Инвалидность", "Дата создания"
                    };
                    case "Социальный мониторинг" -> new String[]{
                            "ID", "Фамилия ребенка", "Имя ребенка",
                            "Отчество ребенка", "ID ребенка", "Дата фиксации", "Тип мониторинга", "Старое значение", "Новое значение",
                            "Причина изменения", "Фамилия сотрудника", "Имя сотрудника", "Отчество сотрудника", "ID пользователя"
                    };
                    case "Жилищные права" -> new String[]{
                            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка",
                            "Наличие жилья", "Форма собственности", "Дата регистрации",
                            "Город", "Улица", "Здание"
                    };
                    case "Очередь на получение жилья" -> new String[]{
                            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка", "Номер в очереди",
                            "Дата постановки в очередь", "Ожидаемая дата выдачи", "Текущий шаг"
                    };
                    default -> new String[0];
                };

                loadData(tableTitle, sql, displayColumns);
            }
        }
    }

    /**
     * Загружает данные из БД в фоновом потоке и обновляет таблицу.
     */

    private void loadData(String tableName, String sql, String[] columns) {
        // Блокируем UI
        tableView.setDisable(true);
        tableView.setPlaceholder(new Label("Загрузка..."));

        // Запускаем загрузку в фоне
        Task<ObservableList<Map<String, Object>>> task = new Task<>() {
            // Map = колонка + значение
            @Override
            protected ObservableList<Map<String, Object>> call() throws Exception {
                // Список строк для таблицы
                List<Map<String, Object>> data = new ArrayList<>();

                try (ResultSet rs = DBConnector.query(sql)) {
                    while (rs.next()) {
                        // Словарь {Название колонки, Значение}
                        // 1 Map = 1 строка с n количеством значений и n количеством ключей
                        // Map может содержать несколько ключей и значений соответственно
                        Map<String, Object> row = new HashMap<>();
                        for (int i = 0; i < columns.length; i++) {
                            // добавление в Map ключа с названием колонки и значением к нему
                            // колонки берутся из массива с колонками
                            Object obj = rs.getObject(i + 1);

                            if (obj != null && !(obj.toString()).matches("[0-9.]*") &&
                                    !columns[i].equals("Тип мониторинга") &&
                                    !columns[i].equals("Наличие жилья") &&
                                    !columns[i].equals("Форма собственности") &&
                                    !columns[i].equals("Текущий шаг")
                            ) {
                                obj = SecurityUtil.decryptSafe((String) obj, key);
                            }

                            row.put(columns[i], obj);
                        }

                        // добавление строки в список строк
                        data.add(row);
                    }
                }

                StaticObjects.setCurrentTable(tableName, data);
                return FXCollections.observableArrayList(data);
            }
        };

        // Когда загрузка завершена
        task.setOnSucceeded(e -> {
            // очищаем колонки
            tableView.getColumns().clear();

            // Соаздем колонки для таблицы
            for (String colName : columns) {
                // колонка состоит из "типа строки таблицы" и "тип данных в колонке"
                // то-есть строка = Map
                TableColumn<Map<String, Object>, String> col = new TableColumn<>(colName);

                // для данной колонки и для данной ячейки устанавливаем значение
                col.setCellValueFactory(cell -> {
                    Object value = cell.getValue().get(colName);
                    return new SimpleStringProperty(value != null ? value.toString() : "");
                });
                tableView.getColumns().add(col);
            }

            // Устанавливаем данные
            tableView.setItems(task.getValue());
            tableView.setDisable(false);
            tableView.setPlaceholder(new Label("Готово"));
        });

        // Если ошибка
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
            case null:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + StaticObjects.getCurrentTableName());
        }
    }
}