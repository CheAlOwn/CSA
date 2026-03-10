package com.chealown.csa;

import com.chealown.csa.DataBase.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage currentStage;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("FXML/AuthorizationPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();

        currentStage = stage;
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        MainApplication.currentStage = currentStage;
    }
}
