package com.chealown.csa.Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchUtil {

    // static final, т.к. метод static. Формат под ваш UI: "dd.MM.yyyy"
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Map<String, Object>> searchData(
            List<Map<String, Object>> currentData,
            String searchText,
            String[] searchColumns,
            String[] filterData,
            String[] filterColumns,
            String[] dateColumns,
            String[] startDate,
            String[] endDate) {

        List<Map<String, Object>> result = new ArrayList<>();
        if (currentData == null) return result;

        // 1. Парсим границы дат заранее (оптимизация)
        int dateLen = (dateColumns != null) ? dateColumns.length : 0;
        LocalDate[] starts = new LocalDate[dateLen];
        LocalDate[] ends = new LocalDate[dateLen];

        for (int i = 0; i < dateLen; i++) {
            String s = (startDate != null && i < startDate.length) ? startDate[i] : null;
            String e = (endDate != null && i < endDate.length) ? endDate[i] : null;
            starts[i] = parseSafe(s);
            ends[i] = parseSafe(e);
        }

        String searchLower = (searchText != null) ? searchText.trim().toLowerCase() : null;

        // 2. Один проход по всем записям
        for (Map<String, Object> row : currentData) {
            if (row == null) continue;

            // 🔍 Текстовый поиск (ИЛИ по колонкам)
            if (searchLower != null && !searchLower.isEmpty() && searchColumns != null) {
                boolean found = false;
                for (String col : searchColumns) {
                    Object val = row.get(col);
                    if (val != null && val.toString().toLowerCase().contains(searchLower)) {
                        found = true;
                        break;
                    }
                }
                if (!found) continue; // не нашли текст → следующая строка
            }

            // 🎛 Фильтры (И по всем указанным колонкам)
            if (filterData != null && filterColumns != null && filterData.length == filterColumns.length) {
                boolean allMatch = true;
                for (int i = 0; i < filterColumns.length; i++) {
                    String expected = filterData[i];
                    if (expected == null || expected.trim().isEmpty() || expected.equals("Все")) {
                        continue;
                    }
                    Object actual = row.get(filterColumns[i]);
                    if (actual == null || !actual.toString().equals(expected.trim())) {
                        allMatch = false;
                        break;
                    }
                }
                if (!allMatch) continue;
            }

            // 📅 Диапазон дат (проверка каждой указанной колонки)
            boolean datesOk = true;
            for (int i = 0; i < dateLen; i++) {
                LocalDate start = starts[i];
                LocalDate end = ends[i];
                if (start == null && end == null) continue; // для этой колонки диапазон не задан

                Object dateObj = row.get(dateColumns[i]);
                LocalDate rowDate = parseRowDate(dateObj);

                if (rowDate == null) {
                    datesOk = false;
                    break; // Строка без даты → не проходит фильтр (можно изменить на true, если нужно пропускать)
                }
                if (start != null && rowDate.isBefore(start)) {
                    datesOk = false;
                    break;
                }
                if (end != null && rowDate.isAfter(end)) {
                    datesOk = false;
                    break;
                }
            }
            if (!datesOk) continue;

            // ✅ Строка прошла все проверки
            result.add(row);
        }

        return result;
    }

    /**
     * Парсит дату из строки в формате dd.MM.yyyy
     */
    private static LocalDate parseSafe(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(dateStr.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Универсальный парсинг даты из значения карты
     */
    private static LocalDate parseRowDate(Object obj) {
        if (obj == null) return null;
        if (obj instanceof LocalDate) return (LocalDate) obj;
        if (obj instanceof java.time.LocalDateTime) return ((java.time.LocalDateTime) obj).toLocalDate();
        if (obj instanceof java.util.Date)
            return ((java.util.Date) obj).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        if (obj instanceof java.sql.Date) return ((java.sql.Date) obj).toLocalDate();
        if (obj instanceof String) return parseSafe((String) obj);
        return null;
    }
}