package com.chealown.csa.Entities;

import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

public class Service {
    static User currentUser;

    public void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type, msg);
        alert.setTitle(title);
        alert.show();
    }

    //TODO: сделать реализацию переключения сцен
    public void switchPage(String title, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("FXML/" + path + ".fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.getCurrentStage().setTitle(title);
        MainApplication.getCurrentStage().setScene(scene);

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Service.currentUser = currentUser;
    }
}
