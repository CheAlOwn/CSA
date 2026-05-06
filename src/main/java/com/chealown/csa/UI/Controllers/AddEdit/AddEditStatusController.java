package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.Status;
import com.chealown.csa.DataBase.Repositories.StatusRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddEditStatusController {

    @FXML
    private Button btnSave;

    @FXML
    private Button exitBtn;

    @FXML
    private Label pageName;

    @FXML
    private TextField statusNameTF;

    private Status status = (Status) StaticObjects.getSelectedObject();

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

        if (status != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        statusNameTF.setText(status.getStatusName());
    }

    private void saveChanges() throws IOException {
        if (statusNameTF.getText().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (status == null) {
                status = new Status();
            }
            status.setStatusName(statusNameTF.getText());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (StatusRepository.save(status)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
    }

}
