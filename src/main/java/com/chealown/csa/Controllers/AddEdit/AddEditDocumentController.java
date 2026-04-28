package com.chealown.csa.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Models.TemplateField;
import com.chealown.csa.DataBase.Repositories.TemplateFieldRepository;
import com.chealown.csa.DataBase.Repositories.TemplateRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEditDocumentController {

    @FXML
    private VBox docFieldsVB;

    @FXML
    private Button exitBtn;

    @FXML
    private Label pageName;

    @FXML
    private ComboBox<String> documentTypeCB;

    private Document document = StaticObjects.getDocument();
    private ArrayList<TemplateDocument> templates;
    ArrayList<FieldTemplateController> fieldTemplateControllers = new ArrayList<>();


    @FXML
    private void initialize() throws SQLException {
        // ПОЛУЧИТЬ ШАБЛОНЫ
        templates = TemplateRepository.getAllData();

        // ЗАПОЛНЕНИЕ ТИПАМИ ШАБЛОНОВ
        for (TemplateDocument template : templates)
            documentTypeCB.getItems().add(template.getName());

        pageName.setText("Добавление");
        if (document != null) {
            pageName.setText("Редактирование");
//            loadData();
        }

        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
//
//    private void loadData() {
//        for (TemplateDocument template: templates) {
//            if (document.getTemplateId() == template.getId()) {
//                documentTypeCB.getSelectionModel().select(template.getName());
//                break;
//            }
//        }
//
//
//
//
//    }

    @FXML
    private void chooseTemplate(ActionEvent actionEvent) throws SQLException, IOException {
        docFieldsVB.getChildren().clear();

        // ПОЛУЧЕНИЕ ИЗ БД СПИСКА ПОЛЕЙ ПО ОПРЕДЕЛЕННОМУ ШАБЛОНУ
        int selectedTemplate = templates.get(documentTypeCB.getSelectionModel().getSelectedIndex()).getId();
        ArrayList<TemplateField> fields = TemplateFieldRepository.getAllData(selectedTemplate);

        // СОХРАНЯЕМ КОНТРОЛЛЕРЫ ЧТОБЫ ПОТОМ ПРИ СОХРАНЕНИИ ПОЛУЧИТ ДАННЫЕ ИЗ ПОЛЕЙ
        // ЗАПОЛНЕНИЕ БОКСА ПОЛЯМИ И ТЕКСТОМ
        for (TemplateField field : fields) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("FXML/AddEdit/FieldTemplate-view.fxml"));
            Node node = loader.load();
            FieldTemplateController controller = loader.getController();
            controller.setLabel(field);
            fieldTemplateControllers.add(controller);
            docFieldsVB.getChildren().add(node);
        }

        Button saveBtn = new Button();
        saveBtn.setStyle(
                "-fx-background-color: #6478E9; " +
                        "-fx-text-fill: #fff; " +
                        "-fx-background-radius: 20; " +
                        "-fx-pref-height: 78; " +
                        "-fx-pref-width: 548; " +
                        "-fx-font-size: 20"
        );
        saveBtn.setText("Сохранить");

        docFieldsVB.getChildren().add(saveBtn);
    }
}
