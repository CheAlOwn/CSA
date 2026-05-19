package com.chealown.csa;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityLogUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        SecurityLogUtil.log(
                SecurityLogUtil.EventType.SYSTEM_STARTUP,
                SecurityLogUtil.EventResult.SUCCESS,
                null,
                "Запуск работы приложения"
        );
    }

    @Override
    public void stop() throws Exception {
        if (StaticObjects.getCurrentUser() != null) {
            SecurityLogUtil.log(
                    SecurityLogUtil.EventType.SYSTEM_SHUTDOWN,
                    SecurityLogUtil.EventResult.SUCCESS,
                    SecurityLogUtil.getCurrentSubject(),
                    "Завершение работы приложения"
            );
        }
        DBConnector.disconnect();
        super.stop();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        MainApplication.currentStage = currentStage;
    }
}
