package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.DocumentField;
import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.DataBase.Repositories.DocumentFieldRepository;
import com.chealown.csa.DataBase.Repositories.DocumentRepository;
import com.chealown.csa.DataBase.Repositories.TemplateFieldRepository;
import com.chealown.csa.DataBase.Repositories.TemplateRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.Entities.TemplateProcessor;
import com.chealown.csa.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddEditDocumentController {

    @FXML
    public Button exportDocBtn;

    @FXML
    private TextField documentNameTF;

    @FXML
    private VBox docFieldsVB;

    @FXML
    private Button exitBtn;

    @FXML
    private Label pageName;

    @FXML
    private ComboBox<String> documentTypeCB;

    private Document document = (Document) StaticObjects.getSelectedObject();
    private ArrayList<TemplateDocument> templates;
    private ArrayList<TemplateField> tmpFields;
    private ArrayList<FieldTemplateController> fieldTemplateControllers = new ArrayList<>();

    private Map<String, String> currentDataMap = new HashMap<>();
    private String currentTemplatePath = "";

    @FXML
    private void initialize() throws SQLException, IOException {
        templates = TemplateRepository.getAllData();

        for (TemplateDocument template : templates)
            documentTypeCB.getItems().add(template.getName());

        pageName.setText("Добавление");
        if (document != null) {
            pageName.setText("Редактирование");
            documentTypeCB.getSelectionModel().select(document.getTemplate());
            loadData();
        }

        setupExportButton();

        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadData() throws SQLException, IOException {
        documentNameTF.setText(document.getLabel());
        documentTypeCB.setManaged(false);
        documentTypeCB.setVisible(false);

        addFieldsToForm();

        ArrayList<DocumentField> docFields = DocumentFieldRepository.getAllData(document.getId());
        for (DocumentField field : docFields) {
            for (int i = 0; i < tmpFields.size(); i++) {
                if (field.getFieldId() == tmpFields.get(i).getId()) {
                    fieldTemplateControllers.get(i).setValueTF(field);
                    break;
                }
            }
        }

        addSaveBtnToForm();
    }

    @FXML
    private void chooseTemplate(ActionEvent actionEvent) throws SQLException, IOException {
        docFieldsVB.getChildren().clear();
        fieldTemplateControllers.clear();
        currentDataMap.clear();

        addFieldsToForm();

        addSaveBtnToForm();
    }

    private void addFieldsToForm() throws SQLException, IOException {
        int selectedTemplate = templates.get(documentTypeCB.getSelectionModel().getSelectedIndex()).getId();
        tmpFields = TemplateFieldRepository.getAllData(selectedTemplate);

        currentTemplatePath = templates.get(documentTypeCB.getSelectionModel().getSelectedIndex()).getFilePath();

        for (TemplateField field : tmpFields) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("FXML/AddEdit/FieldTemplate-view.fxml"));
            Node node = loader.load();
            FieldTemplateController controller = loader.getController();
            controller.setLabel(field);
            fieldTemplateControllers.add(controller);
            docFieldsVB.getChildren().add(node);
        }
    }

    private void addSaveBtnToForm() {
        Button saveBtn = new Button();
        saveBtn.getStyleClass().add("accent-button");
        saveBtn.setPrefWidth(548);
        saveBtn.setText("Сохранить");
        saveBtn.setOnAction(event -> {
            try {
                saveChanges();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        docFieldsVB.getChildren().add(saveBtn);
        VBox.setMargin(saveBtn, new Insets(16, 0, 16, 0));

    }

    private void saveChanges() throws Exception {
        boolean isStopped = false;

        for (int i = 0; i < tmpFields.size(); i++) {
            if (tmpFields.get(i).isRequired() && fieldTemplateControllers.get(i).getValueTF().trim().isEmpty()) {
                isStopped = true;
            }
        }

        if (isStopped) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText() + " записи", "Не все обязательные поля заполнены");
            return;
        }

        collectCurrentData();

        int documentId = (document == null) ? DocumentRepository.save(new Document(
                String.valueOf(templates.get(documentTypeCB.getSelectionModel().getSelectedIndex()).getId()),
                documentNameTF.getText(),
                LocalDateTime.now().toString(),
                StaticObjects.getCurrentUser().getId()
        )) : document.getId();

        ArrayList<DocumentField> documentFields = (document == null) ? new ArrayList<>() : DocumentFieldRepository.getAllData(documentId);

        if (document == null) {
            for (int i = 0; i < fieldTemplateControllers.size(); i++) {
                documentFields.add(new DocumentField(
                        documentId,
                        tmpFields.get(i).getId(),
                        fieldTemplateControllers.get(i).getValueTF()
                ));
            }
        } else {
            for (int i = 0; i < fieldTemplateControllers.size(); i++) {
                documentFields.get(i).setValue(fieldTemplateControllers.get(i).getValueTF());
            }
        }

        boolean isSaved = DocumentFieldRepository.save(documentFields);

        if (isSaved) {
            ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText() + " записи", pageName.getText() + " записи проведено успешно");
            ManageUtil.switchPage("Главная", "MainPage-view");
        } else {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText() + " записи", pageName.getText() + " записи не было проведено");
        }
    }

    private void collectCurrentData() {
        currentDataMap.clear();
        for (int i = 0; i < fieldTemplateControllers.size(); i++) {
            String placeholder = tmpFields.get(i).getPlaceholder();
            String value = fieldTemplateControllers.get(i).getValueTF();
            currentDataMap.put(placeholder, value);
        }
    }

    private void exportDocument() {
        if (currentTemplatePath == null || currentTemplatePath.isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт документа", "Путь к шаблону не найден");
            return;
        }

        if (currentDataMap.isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт документа", "Нет данных для экспорта. Сначала сохраните документ.");
            return;
        }

        String originalExtension = currentTemplatePath.substring(currentTemplatePath.lastIndexOf("."));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить документ");

        switch (originalExtension.toLowerCase()) {
            case ".docx":
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(".docx", "*.docx"));
                break;
            case ".odt":
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(".odt", "*.odt"));
                break;
            default:
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Все файлы", "*.*"));
        }

        String defaultName = documentNameTF.getText().trim().isEmpty() ? "документ" : documentNameTF.getText();
        fileChooser.setInitialFileName(defaultName + originalExtension);

        File file = fileChooser.showSaveDialog(exportDocBtn.getScene().getWindow());
        if (file != null) {
           String filePath = file.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(originalExtension.toLowerCase())) {
                filePath += originalExtension;
            }

            try {
                TemplateProcessor.processTemplate(
                        currentTemplatePath,
                        filePath,
                        currentDataMap
                );

                ManageUtil.showAlert(Alert.AlertType.INFORMATION, "Экспорт документа",
                        "Документ успешно экспортирован");
            } catch (Exception e) {
                e.printStackTrace();
                ManageUtil.showAlert(Alert.AlertType.ERROR, "Экспорт документа",
                        "Ошибка экспорта: " + e.getMessage());
            }
        }
    }

    private void setupExportButton() {
        if (exportDocBtn == null) return;

        exportDocBtn.setOnAction(e -> {
            if (documentTypeCB.getSelectionModel().isEmpty()) {
                ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт", "Сначала выберите шаблон");
                return;
            }

            if (fieldTemplateControllers.isEmpty()) {
                ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт", "Сначала выберите шаблон");
                return;
            }

            if (documentNameTF.getText().trim().isEmpty()) {
                ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт", "Сначала введите название документа");
                return;
            }

            collectCurrentData();

            if (currentDataMap.isEmpty()) {
                ManageUtil.showAlert(Alert.AlertType.WARNING, "Экспорт", "Нет данных для экспорта");
                return;
            }

            exportDocument();
        });
    }
}