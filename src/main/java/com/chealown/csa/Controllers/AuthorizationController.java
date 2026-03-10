package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.Entities.Service;
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

    Service service = new Service();

    @FXML
    private void initialize() throws ClassNotFoundException {

        // соединение с бд
        DBConnector.connect();


        entryTF.setOnAction(actionEvent -> {
            try {
                if(checkUser(loginTF.getText(), passwordTF.getText())) {
                    service.switchPage("Главная", "MainPage-view");
                } else {
                    service.showAlert(Alert.AlertType.WARNING, "Авторизация", "Неверно введены логин или пароль");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



    }

    // проверка существует ли пользователь
    private boolean checkUser(String login, String password) throws SQLException {
        ResultSet rs = DBConnector.executeQuery(String.format("SELECT u.id, login, \"password\", second_name, first_name, patronymic, post_name \n" +
                "FROM \"user\" u\n" +
                "inner join employee e on u.id_employee = e.id\n" +
                "inner join post p on e.post = p.id\n" +
                "where login='%s' AND password='%s'", login, password));


        if (rs.next()) {
            Service.setCurrentUser(new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("second_name"), rs.getString("first_name"), rs.getString("patronymic"), rs.getString("post_name")));
            return true;
        }
        return false;
    }
}
