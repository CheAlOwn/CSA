package com.chealown.csa.Entities;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class MaskUtil {
    public static void applyDateMask(TextField textField) {
        UnaryOperator<TextFormatter.Change> dateFilter = change -> {
            String newText = change.getControlNewText();
            if (!newText.matches("[0-9.]*")) {
                return null;
            }
            String digits = newText.replaceAll("\\.", "");
            if (digits.length() > 8) {
                return null;
            }
            StringBuilder formatted = new StringBuilder();
            int len = digits.length();
            if (len > 0) {
                formatted.append(digits.charAt(0));
            }
            if (len > 1) {
                formatted.append(digits.charAt(1));
                int day = Integer.parseInt(digits.substring(0, 2));
                if (day > 31) return null;
            }
            if (len > 2) {
                formatted.append('.');
                formatted.append(digits.charAt(2));
            }
            if (len > 3) {
                formatted.append(digits.charAt(3));
                int month = Integer.parseInt(digits.substring(2, 4));
                if (month > 12) return null;
            }
            if (len > 4) {
                formatted.append('.');
                for (int i = 4; i < len; i++) {
                    formatted.append(digits.charAt(i));
                }
            }
            String finalText = formatted.toString();
            change.setText(finalText);
            change.setRange(0, change.getControlText().length());
            change.setCaretPosition(finalText.length());
            change.setAnchor(finalText.length());
            return change;
        };

        TextFormatter<String> formatter = new TextFormatter<>(
                new DefaultStringConverter(),
                "",
                dateFilter
        );
        textField.setTextFormatter(formatter);
        textField.setPromptText("ДД.ММ.ГГГГ");
    }

    public static String formattedDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(formatter);
    }

    public static void applyNumberMask(TextField textField, int maxLength) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (!newText.matches("\\d*")) {
                return null;
            }
            if (newText.length() > maxLength) {
                return null;
            }
            return change;
        }));
    }

    public static void applySnilsMask(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            String digits = newText.replaceAll("\\D", "");
            if (digits.length() > 11) {
                digits = digits.substring(0, 11);
            }
            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < digits.length(); i++) {
                if (i == 3 || i == 6) {
                    formatted.append("-");
                } else if (i == 9) {
                    formatted.append(" ");
                }
                formatted.append(digits.charAt(i));
            }
            String result = formatted.toString();
            if (!result.equals(newText)) {
                change.setText(result);
                change.setCaretPosition(result.length());
                change.setAnchor(result.length());
            }
            return change;
        };
        textField.setTextFormatter(new TextFormatter<>(filter));
    }
}
