package com.chealown.csa;

import com.chealown.csa.Entities.TemplateProcessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainApplication extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("FXML/AuthorizationPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        currentStage = stage;
//        testExample();
    }


    private void testExample() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("${name}", "Leonid");
        map.put("${age}", "19");

        TemplateProcessor.processTemplate("templates/1777405762731_template.odt", String.format("documents/%s_document.odt", 2), map);
    }


    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        MainApplication.currentStage = currentStage;
    }
}
