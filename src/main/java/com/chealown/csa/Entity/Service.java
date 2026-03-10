package com.chealown.csa.Entity;

import javafx.scene.control.Alert;

public class Service {
    public void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type, msg);
        alert.setTitle(title);
        alert.show();
    }

    //TODO: сделать реализацию переключения сцен
    public void switchPage(String title, String path) {

    }
}
