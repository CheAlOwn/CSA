package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.TemplateParser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddEditTemplateDocController {

    @FXML
    private Button btnSave;

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

    private TemplateDocument template;
    private File uploadFile;
    private final List<TemplateField> detectedFields = new ArrayList<>();

    @FXML
    private void initialize() {
        setupTable();
    }

    private void setupTable() {
        placeholderCol.setCellValueFactory(new PropertyValueFactory<>("placeholder"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));

        // Настраиваем редактирование label
        setupLabelCell();
    }

    private void setupLabelCell() {
        labelCol.setCellFactory(column -> new TableCell<TemplateField, String>() {
            private final TextField textField = new TextField();

            {
                textField.setOnAction(e -> {
                    TemplateField field = getTableView().getItems().get(getIndex());
                    String newLabel = textField.getText();
                    field.setLabel(newLabel);

                    // Автоматически обновляем имя поля для маппинга
                    if (field.getLabel() == null || field.getLabel().equals(field.getPlaceholder())) {
                        field.setLabel(generateFieldName(newLabel));
                    }

                    commitEdit(newLabel);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    textField.setText(item);
                    setGraphic(textField);
                }
            }
        });
    }

    @FXML
    private void chooseTemplate(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите шаблон документа");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All supported", "*.docx", "*.odt"));

        uploadFile = fileChooser.showOpenDialog(chooseTemplateBtn.getScene().getWindow());

        if (uploadFile != null) {
            try {
                List<String> placeholders = TemplateParser.extractPlaceholders(uploadFile.getAbsolutePath());
                if (placeholders.isEmpty())
                    ManageUtil.showAlert(Alert.AlertType.WARNING, "Обнаружение плейсхолдеров", "В шаблоне не найдено плейсхолдеров. Используйте формат {{fieldName}} или ${fieldName}");

                detectedFields.clear();
                for (String placeholder : placeholders) {
                    String fieldName = extractFieldName(placeholder);
                    TemplateField field = new TemplateField(placeholder, fieldName);

                    field.setLabel(formatLabel(fieldName));
                    detectedFields.add(field);
                }

                fieldsTV.setItems(FXCollections.observableArrayList(detectedFields));

                String baseName = uploadFile.getName();
                baseName = baseName.replaceFirst("\\.(docx|odt)$", "");
                templateTF.setText(baseName);

                ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Выгрузка шаблона", "Шаблон выгружен. Найдено плейсхолдеров: " + placeholders.size());

            } catch (IOException e) {
                ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка", "Ошибка чтения файла: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                ManageUtil.showAlert(Alert.AlertType.ERROR, "Ошибка", "Неожиданная ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveChanges(ActionEvent actionEvent) {
        if (uploadFile == null) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Сохранение шаблона", "Загрузите файл шаблона");
            return;
        }

        if (templateTF.getText().trim().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Сохранение шаблона", "Введите название шаблона");
            return;
        }

        if (detectedFields.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "В шаблоне нет полей. Сохранить шаблон без полей?");
            alert.setTitle("Сохранение шаблона");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() != null && result.get().equals(ButtonType.CANCEL)) {
                return;
            }
        }

        List<String> validationErrors = validateFields();
        if (!validationErrors.isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Сохранение шаблона", "Ошибки в полях:\n" + String.join("\n", validationErrors));
            return;
        }

        try {
            Path templatesDir = Paths.get("templates");
            Files.createDirectories(templatesDir);

            String timestamp = String.valueOf(System.currentTimeMillis());
            String extension = uploadFile.getName().substring(uploadFile.getName().lastIndexOf("."));
            String newFileName = timestamp + "_" + sanitizeFileName(templateTF.getText()) + extension;
            Path targetPath = templatesDir.resolve(newFileName);

            Files.copy(uploadFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            template = new TemplateDocument();
            template.setName(templateTF.getText().trim());
            template.setFilePath("templates/" + newFileName);
            template.setFields(new ArrayList<>(detectedFields));
            template.setCreatedAt(LocalDateTime.now());

            ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Сохранение шаблона", "Шаблон \"" + template.getName() + "\" успешно сохранен!");

            /*
             * TODO: сделать закрытие формы
             * */

        } catch (IOException e) {
            ManageUtil.showAlert(Alert.AlertType.ERROR, "Сохранение шаблона", "Ошибка сохранения файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String extractFieldName(String placeholder) {
        return placeholder
                .replace("{{", "")
                .replace("}}", "")
                .replace("${", "")
                .replace("}", "");
    }

    private String formatLabel(String fieldName) {
        // Преобразуем camelCase или snake_case в читаемый текст
        String label = fieldName
                .replace("_", " ")
                .replaceAll("([a-z])([A-Z])", "$1 $2");

        // Делаем первую букву заглавной
        if (!label.isEmpty()) {
            label = Character.toUpperCase(label.charAt(0)) + label.substring(1).toLowerCase();
        }
        return label;
    }

    private String generateFieldName(String label) {
        // Генерируем имя поля из label (транслитерация для кириллицы)
        return label
                .toLowerCase()
                .replace(" ", "_")
                .replace("ё", "e")
                .replace("й", "i")
                .replace("ц", "ts")
                .replace("у", "u")
                .replace("к", "k")
                .replace("е", "e")
                .replace("н", "n")
                .replace("г", "g")
                .replace("ш", "sh")
                .replace("щ", "sch")
                .replace("з", "z")
                .replace("х", "h")
                .replace("ъ", "")
                .replace("ф", "f")
                .replace("ы", "y")
                .replace("в", "v")
                .replace("а", "a")
                .replace("п", "p")
                .replace("р", "r")
                .replace("о", "o")
                .replace("л", "l")
                .replace("д", "d")
                .replace("ж", "zh")
                .replace("э", "e")
                .replace("я", "ya")
                .replace("ч", "ch")
                .replace("с", "s")
                .replace("м", "m")
                .replace("и", "i")
                .replace("т", "t")
                .replace("ь", "")
                .replace("б", "b")
                .replace("ю", "yu")
                .replaceAll("[^a-z0-9_]", "");
    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    private List<String> validateFields() {
        List<String> errors = new ArrayList<>();
        for (int i = 0; i < detectedFields.size(); i++) {
            TemplateField field = detectedFields.get(i);
            if (field.getLabel() == null || field.getLabel().trim().isEmpty()) {
                errors.add("Строка " + (i + 1) + ": не заполнен label");
            }
            if (field.getPlaceholder() == null || field.getPlaceholder().isEmpty()) {
                errors.add("Строка " + (i + 1) + ": пустой плейсхолдер");
            }
        }
        return errors;
    }
}
