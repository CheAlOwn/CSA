package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.DataBase.Repositories.UserRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AddEditUserController {

    @FXML
    private Button btnSave;

    @FXML
    private Button chooseEmployeeBtn;

    @FXML
    private TextField employeeIdTF;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField loginTF;

    @FXML
    private Label pageName;

    @FXML
    private PasswordField passwordTF;

    private User user = (User) StaticObjects.getSelectedObject();

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

        if (user != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        loginTF.setText(user.getLogin());
        employeeIdTF.setText(String.valueOf(user.getEmployeeId()));
    }

    private void saveChanges() throws IOException {
        if (
                loginTF.getText().isEmpty() ||
                        passwordTF.getText().isEmpty() ||
                        employeeIdTF.getText().isEmpty()
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (user == null) {
                user = new User();
            }

            user.setEmployeeId(Integer.parseInt(employeeIdTF.getId()));
            user.setLogin(loginTF.getText());
            user.setPassword(SecurityUtil.hashPassword(passwordTF.getText()));

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (UserRepository.save(user)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
    }

}
