package com.chealown.csa.Entities;

import com.chealown.csa.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ManageUtil {
    public static void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type, msg);
        alert.setTitle(title);
        alert.show();
    }

    public static void switchPage(String title, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("FXML/" + path + ".fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.getCurrentStage().setTitle(title);
        MainApplication.getCurrentStage().setScene(scene);

        // Получить контроллер главной страницы
        if (path.equals("MainPage-view")) {
            StaticObjects.setController(loader.getController());
        }

    }
}
