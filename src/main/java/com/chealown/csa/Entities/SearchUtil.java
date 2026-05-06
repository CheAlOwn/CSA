package com.chealown.csa.Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static List<Object> searchData(
            List<Object> object,
            List<Map<String, Object>> currentData,
            String searchText,
            String[] searchColumns,
            String[] filterData,
            String[] filterColumns,
            String[] dateColumns,
            String[] startDate,
            String[] endDate) {

        List<Object> result = new ArrayList<>();
        if (currentData.isEmpty()) return result;

        // Парсим границы дат заранее
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

        for (int i = 0; i < currentData.size(); i++) {
            Map<String, Object> row = currentData.get(i);
            if (row == null) continue;

            // Текстовый поиск (ИЛИ по колонкам)
            if (searchLower != null && !searchLower.isEmpty() && searchColumns != null) {
                boolean found = false;
                for (int j = 0; j < searchColumns.length; j++) {
                    String col = searchColumns[j];
                    Object val = row.get(col);
                    if (val != null && val.toString().toLowerCase().startsWith(searchLower)) {
                        found = true;
                        break;
                    }
                }
                if (!found) continue;
            }

            // Фильтры (И по всем указанным колонкам)
            if (filterData != null && filterColumns != null &&
                    filterData.length == filterColumns.length) {
                boolean allMatch = true;
                for (int k = 0; k < filterColumns.length; k++) {
                    String expected = filterData[k];
                    if (expected == null || expected.trim().isEmpty() || expected.equals("Все")) {
                        continue;
                    }
                    Object actual = row.get(filterColumns[k]);
                    if (actual == null || !actual.toString().trim().equalsIgnoreCase(expected.trim())) {
                        allMatch = false;
                        break;
                    }
                }
                if (!allMatch) continue;
            }

            // Диапазон дат
            boolean datesOk = true;
            for (int m = 0; m < dateLen; m++) {
                LocalDate start = starts[m];
                LocalDate end = ends[m];
                if (start == null && end == null) continue;

                Object dateObj = row.get(dateColumns[m]);
                LocalDate rowDate = parseRowDate(dateObj);

                if (rowDate == null) {
                    datesOk = false;
                    break;
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

            result.add(object.get(i));
        }

        return result;
    }

    private static LocalDate parseSafe(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        try {
            return LocalDate.parse(dateStr.trim(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка парсинга даты: " + dateStr);
            return null;
        }
    }

    private static LocalDate parseRowDate(Object obj) {
        if (obj == null) return null;
        if (obj instanceof LocalDate) return (LocalDate) obj;
        if (obj instanceof java.time.LocalDateTime)
            return ((java.time.LocalDateTime) obj).toLocalDate();
        if (obj instanceof java.util.Date)
            return ((java.util.Date) obj).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        if (obj instanceof java.sql.Date) return ((java.sql.Date) obj).toLocalDate();
        if (obj instanceof String) return parseSafe((String) obj);
        return null;
    }
}
