package com.chealown.csa;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Scanner;

public class MainApplication extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("FXML/AuthorizationPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Авторизация");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        currentStage = stage;

//        DBConnector.connect();
//        SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");



        // Временный пункт для добавления данных без использования форм

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Сколько значений?");
//
//        int count = scanner.nextInt();
//        scanner.nextLine();
//
//        for (int i = 0; i < count; i++) {
//            System.out.println("Введите значение " + (i + 1));
//            insertData(
//                    "INSERT INTO \"user\"\n" +
//                            "(password_hash)\n" +
//                            "VALUES(?);",
//                    key,
//                    scanner.nextLine()
//            );
//
//            System.out.println("Добавлена строка " + (i + 1));
//        }
    }

    public void insertData(String sql, SecretKey encryptionKey, Object... params) {
        Object[] encryptedFields = new Object[params.length];


        for (int i = 0; i < params.length; i++) {
//            encryptedFields[i] = SecurityUtil.encryptSafe((String) params[i], encryptionKey);
            encryptedFields[i] = SecurityUtil.hashPassword((String) params[i]);
            System.out.println(encryptedFields[i]);
        }

        DBConnector.update(sql, encryptedFields);
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        MainApplication.currentStage = currentStage;
    }
}
