package com.chealown.csa;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.TemplateParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
    }
//
//    private static void createTestDocx(String filePath) throws Exception {
//        try (XWPFDocument doc = new XWPFDocument()) {
//            // Параграф с текстом
//            XWPFParagraph p1 = doc.createParagraph();
//            XWPFRun r1 = p1.createRun();
//            r1.setText("Привет, {{username}}!");
//
//            // Ещё один параграф
//            XWPFParagraph p2 = doc.createParagraph();
//            XWPFRun r2 = p2.createRun();
//            r2.setText("Ваш баланс: ${balance} рублей");
//
//            // Таблица с плейсхолдерами
//            org.apache.poi.xwpf.usermodel.XWPFTable table = doc.createTable(2, 2);
//            table.getRow(0).getCell(0).setText("{{firstName}}");
//            table.getRow(0).getCell(1).setText("${lastName}");
//            table.getRow(1).getCell(0).setText("{{email}}");
//            table.getRow(1).getCell(1).setText("${phone}");
//
//            // Сохраняем
//            try (FileOutputStream out = new FileOutputStream(filePath)) {
//                doc.write(out);
//            }
//        }
//        System.out.println("Тестовый файл создан: " + filePath);
//    }
//
//    private static void testDifferentPlaceholders() throws Exception {
//        String testFile = "test_various.docx";
//
//        try (XWPFDocument doc = new XWPFDocument()) {
//            XWPFParagraph p = doc.createParagraph();
//            XWPFRun r = p.createRun();
//
//            // Разные форматы плейсхолдеров
//            r.setText("""
//                Тестирование разных форматов:
//                1. Простой: {{name}}
//                2. С цифрами: {{user123}}
//                3. С нижним подчёркиванием: {{user_name}}
//                4. ${variable}
//                5. ${total_amount}
//                6. Обычный текст без плейсхолдеров
//                7. {{multiple}} и ${together} в одном предложении
//                """);
//
//            try (FileOutputStream out = new FileOutputStream(testFile)) {
//                doc.write(out);
//            }
//        }
//
//        List<String> placeholders = TemplateParser.extractPlaceholders(testFile);
//        System.out.println("Найдено плейсхолдеров: " + placeholders.size());
//        placeholders.forEach(ph -> System.out.println("  - " + ph));
//    }


    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        MainApplication.currentStage = currentStage;
    }
}
