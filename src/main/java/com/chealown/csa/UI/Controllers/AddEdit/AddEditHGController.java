package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.HealthGroup;
import com.chealown.csa.DataBase.Repositories.HealthGroupRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddEditHGController {

    @FXML
    private Button btnSave;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField groupNameTF;

    @FXML
    private Label pageName;

    private HealthGroup group = (HealthGroup) StaticObjects.getSelectedObject();

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

        if (group != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        groupNameTF.setText(group.getGroupName());
    }

    private void saveChanges() throws IOException {
        if (groupNameTF.getText().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (group == null) {
                group = new HealthGroup();
            }
            group.setGroupName(groupNameTF.getText());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (HealthGroupRepository.save(group)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
    }

}
