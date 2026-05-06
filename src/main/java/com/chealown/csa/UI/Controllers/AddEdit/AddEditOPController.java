package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.OrganizationType;
import com.chealown.csa.DataBase.Repositories.OrganizationTypeRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddEditOPController {

    @FXML
    private Button btnSave;

    @FXML
    private Button exitBtn;

    @FXML
    private Label pageName;

    @FXML
    private TextField typeNameTF;

    private OrganizationType type = (OrganizationType) StaticObjects.getSelectedObject();

    @FXML
    private void initialize() {

        btnSave.setOnAction(actionEvent -> {
            try {
                saveChanges();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pageName.setText("Добавление");

        if (type != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        typeNameTF.setText(type.getTypeName());
    }

    private void saveChanges() throws IOException {
        if (typeNameTF.getText().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (type == null) {
                type = new OrganizationType();
            }
            type.setTypeName(typeNameTF.getText());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (OrganizationTypeRepository.save(type)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
    }

}
