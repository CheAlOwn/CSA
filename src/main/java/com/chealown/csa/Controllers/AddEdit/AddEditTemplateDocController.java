package com.chealown.csa.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.DataBase.Repositories.TemplateFieldRepository;
import com.chealown.csa.DataBase.Repositories.TemplateRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.Entities.TemplateParser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddEditTemplateDocController {
    @FXML
    private TableColumn<TemplateField, Boolean> requiredCol;
    @FXML
    private Button chooseTemplateBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TableView<TemplateField> fieldsTV;
    @FXML
    private TableColumn<TemplateField, String> labelCol;
    @FXML
    private Label pageName;
    @FXML
    private TableColumn<TemplateField, String> placeholderCol;
    @FXML
    private TextField templateTF;

    private TemplateDocument template = StaticObjects.getTemplate();
    private File uploadFile;
    private boolean isEditMode = false;

    @FXML
    private void initialize() {
        setupTable();

        System.out.println(template);

        if (StaticObjects.getTemplate() != null) {
            pageName.setText("Редактирование");
            loadTemplateData();
            chooseTemplateBtn.setManaged(false);
        } else {
            pageName.setText("Добавление");
            chooseTemplateBtn.setManaged(true);
        }

        exitBtn.setOnAction(actionEvent -> {
            try {
                StaticObjects.setTemplate(null);
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupTable() {
        placeholderCol.setCellValueFactory(new PropertyValueFactory<>("placeholder"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        requiredCol.setCellValueFactory(new PropertyValueFactory<>("required"));

        fieldsTV.setEditable(true);

        // TextField: коммит по Enter или потере фокуса
        labelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        labelCol.setOnEditCommit(event -> {
            event.getRowValue().setLabel(event.getNewValue());
            fieldsTV.refresh(); // Принудительно обновляем таблицу
        });

        // Исправленный CheckBox
        requiredCol.setCellFactory(column -> new CheckBoxTableCell<TemplateField, Boolean>() {
            private final CheckBox checkBox = new CheckBox();

            {
                // Слушаем изменения CheckBox
                checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                    TemplateField field = getTableRow().getItem();
                    if (field != null) {
                        field.setRequired(isSelected);
                        System.out.println("CheckBox changed for field: " + field.getPlaceholder() +
                                ", required: " + isSelected);
                    }
                });
            }

            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    TemplateField field = getTableRow().getItem();
                    if (field != null) {
                        checkBox.setSelected(field.isRequired());
                        setGraphic(checkBox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }

    private void loadTemplateData() {
        try {
            List<TemplateField> fields = TemplateFieldRepository.getAllData(template.getId());
            fieldsTV.setItems(FXCollections.observableArrayList(fields));
            templateTF.setText(template.getName());
        } catch (Exception e) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка",
                    "Не удалось загрузить данные: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void chooseTemplate(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите шаблон документа");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Документы", "*.docx", "*.odt")
        );

        File file = fileChooser.showOpenDialog(chooseTemplateBtn.getScene().getWindow());
        if (file == null) return;

        uploadFile = file;

        try {
            List<String> placeholders = TemplateParser.extractPlaceholders(file.getAbsolutePath());

            if (placeholders.isEmpty()) {
                ManageUtil.showAlert(Alert.AlertType.WARNING, "Предупреждение",
                        "В шаблоне не найдено плейсхолдеров");
            }

            List<TemplateField> fields = new ArrayList<>();
            for (String placeholder : placeholders) {
                String fieldName = extractFieldName(placeholder);
                TemplateField field = new TemplateField(placeholder, fieldName);
                field.setLabel(formatLabel(fieldName));
                field.setRequired(false);
                fields.add(field);
            }

            fieldsTV.setItems(FXCollections.observableArrayList(fields));

            String name = file.getName();
            name = name.substring(0, name.lastIndexOf("."));
            templateTF.setText(name);

            ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Успех",
                    "Найдено полей: " + placeholders.size());

        } catch (Exception e) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка",
                    "Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
        if (StaticObjects.getTemplate() == null && uploadFile == null) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка", "Загрузите файл шаблона");
            return;
        }

        if (templateTF.getText().trim().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка", "Введите название шаблона");
            return;
        }

        // Гарантированно забираем актуальные данные из таблицы
        List<TemplateField> fields = getCommittedFieldsList();

        if (fields.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "В шаблоне нет полей. Продолжить?");
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.CANCEL) {
                return;
            }
        }

        // Валидация
        List<String> errors = new ArrayList<>();
        for (int i = 0; i < fields.size(); i++) {
            TemplateField f = fields.get(i);
            if (f.getLabel() == null || f.getLabel().trim().isEmpty()) {
                errors.add("Строка " + (i + 1) + ": не заполнен label");
            }
        }

        if (!errors.isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка",
                    String.join("\n", errors));
            return;
        }

        try {
            if (StaticObjects.getTemplate() != null) {
                updateTemplate(fields);
            } else {
                createTemplate(fields);
            }

            StaticObjects.setTemplate(null);
            ManageUtil.switchPage("Главная", "MainPage-view");

        } catch (Exception e) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка",
                    "Ошибка сохранения: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Фиксирует активное редактирование и возвращает копии объектов с актуальными значениями.
     */
    private List<TemplateField> getCommittedFieldsList() {
        List<TemplateField> result = new ArrayList<>();

        fieldsTV.edit(-1, null); // Завершаем редактирование, если ячейка активна
        fieldsTV.refresh();      // Обновляем UI

        for (TemplateField field : fieldsTV.getItems()) {
            System.out.println(field.isRequired());
            TemplateField committed = new TemplateField();
            committed.setId(field.getId());
            committed.setTemplateId(field.getTemplateId());
            committed.setPlaceholder(field.getPlaceholder());
            committed.setLabel(field.getLabel());
            committed.setRequired(field.isRequired());
            committed.setDefaultValue(field.getDefaultValue());
            result.add(committed);
        }
        return result;
    }

    private void createTemplate(List<TemplateField> fields) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String extension = uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."));
        String fileName = timestamp + "_" + sanitizeFileName(templateTF.getText()) + extension;

        Path templatesDir = Paths.get("templates");
        Files.createDirectories(templatesDir);

        Path targetPath = templatesDir.resolve(fileName);
        Files.copy(uploadFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        template = new TemplateDocument();
        template.setFilePath("templates/" + fileName);
        template.setCreatedAt(LocalDateTime.now().toString());
        template.setName(templateTF.getText().trim());

        int templateId = TemplateRepository.save(template);

        for (TemplateField field : fields) {
            field.setTemplateId(templateId);
        }

        if (TemplateFieldRepository.save(fields)) {
            ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Успех", "Шаблон сохранен");
        }
    }

    private void updateTemplate(List<TemplateField> fields) throws SQLException {
        template.setName(templateTF.getText().trim());
        template.setUpdatedAt(LocalDateTime.now().toString());

        int result = TemplateRepository.save(template);

        if (result > 0) {
            for (TemplateField field : fields) {
                field.setTemplateId(template.getId());
            }

            if (TemplateFieldRepository.save(fields)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Успех", "Шаблон обновлен");
            }
        }
    }

    private String extractFieldName(String placeholder) {
        return placeholder.replace("{{", "").replace("}}", "").replace("${", "").replace("}", "");
    }

    private String formatLabel(String fieldName) {
        String label = fieldName.replace("_", " ").replaceAll("([a-z])([A-Z])", "$1 $2");
        if (!label.isEmpty()) {
            label = Character.toUpperCase(label.charAt(0)) + label.substring(1).toLowerCase();
        }
        return label;
    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}