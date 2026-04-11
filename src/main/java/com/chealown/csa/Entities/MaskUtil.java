package com.chealown.csa.Entities;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.UnaryOperator;

public class MaskUtil {
    public static void applyDateMask(TextField textField) {
        UnaryOperator<TextFormatter.Change> dateFilter = change -> {
            String newText = change.getControlNewText();

            // Проверка: только цифры и точки
            if (!newText.matches("[0-9.]*")) {
                return null;
            }

            // Убираем точки для обработки "чистых" цифр
            String digits = newText.replaceAll("\\.", "");

            // Максимальная длина без точек = 8 цифр (ддммгггг)
            if (digits.length() > 8) {
                return null;
            }

            StringBuilder formatted = new StringBuilder();
            int len = digits.length();

            // Формируем строку с точками
            if (len > 0) {
                formatted.append(digits.charAt(0));
            }
            if (len > 1) {
                formatted.append(digits.charAt(1));

                // Проверка дня
                int day = Integer.parseInt(digits.substring(0, 2));
                if (day > 31) return null;
            }
            if (len > 2) {
                formatted.append('.');
                formatted.append(digits.charAt(2));
            }
            if (len > 3) {
                formatted.append(digits.charAt(3));

                // Проверка месяца
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

            // Применяем изменения
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

            // Только цифры
            if (!newText.matches("\\d*")) {
                return null;
            }

            // Ограничение по длине
            if (newText.length() > maxLength) {
                return null;
            }

            return change;
        }));
    }

    public static void applySnilsMask(TextField textField) {
        // Используем TextFormatter вместо Listener
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            // Удаляем все не-цифры
            String digits = newText.replaceAll("\\D", "");

            // Ограничиваем 11 цифрами
            if (digits.length() > 11) {
                digits = digits.substring(0, 11);
            }

            // Форматируем с маской
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

            // Обновляем текст, только если он изменился
            if (!result.equals(newText)) {
                change.setText(result);
                // Устанавливаем позицию курсора в конец
                change.setCaretPosition(result.length());
                change.setAnchor(result.length());
                // Не вызываем setRange!
            }

            return change;
        };

        textField.setTextFormatter(new TextFormatter<>(filter));
    }

}
