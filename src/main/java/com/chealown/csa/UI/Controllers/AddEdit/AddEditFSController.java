package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.FamilySituation;
import com.chealown.csa.DataBase.Repositories.FamilySituationRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddEditFSController {

    @FXML
    private Button btnSave;

    @FXML
    private Button exitBtn;

    @FXML
    private Label pageName;

    @FXML
    private TextField situationNameTF;

    private FamilySituation situation = (FamilySituation) StaticObjects.getSelectedObject();

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

        if (situation != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        situationNameTF.setText(situation.getSituationName());
    }

    private void saveChanges() throws IOException {
        if (situationNameTF.getText().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (situation == null) {
                situation = new FamilySituation();
            }
            situation.setSituationName(situationNameTF.getText());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (FamilySituationRepository.save(situation)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
    }

}
