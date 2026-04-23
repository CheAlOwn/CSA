package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationController {
    @FXML
    private Button entryTF;

    @FXML
    private TextField loginTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private void initialize() throws ClassNotFoundException {

        DBConnector.connect();

        entryTF.setOnAction(actionEvent -> {
            try {
                if (checkUser(loginTF.getText(), passwordTF.getText())) {
                    ManageUtil.switchPage("Главная", "MainPage-view");
                } else {
                    ManageUtil.showAlert(Alert.AlertType.WARNING, "Авторизация", "Неверно введены логин или пароль");
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private boolean checkUser(String login, String password) throws SQLException {
        ResultSet rs = DBConnector.query("SELECT u.id, login, password_hash, e.second_name, e.first_name , e.patronymic, post_name \n" +
                "FROM \"user\" u\n" +
                "inner join employee e on u.id_employee = e.id\n" +
                "inner join post p on e.post = p.id\n" +
                "where login = ?\n", login);

        if (rs.next()) {
            if (SecurityUtil.verifyPassword(password, rs.getString("password_hash"))) {
                StaticObjects.setCurrentUser(new User(rs.getInt("id"), rs.getString("login"), rs.getString("second_name"), rs.getString("first_name"), rs.getString("patronymic"), rs.getString("post_name")));
                return true;
            }

            return false;
        }
        return false;
    }
}
