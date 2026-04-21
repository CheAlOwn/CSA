package com.chealown.csa.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchUtil {
    /**
     * Основной метод поиска
     * @param findData строка для поиска
     * @param data список данных таблицы
     * @param columns колонки, по которым производить поиск
     * @return список найденных записей (может быть пустым)
     */
    public static List<Map<String, Object>> searchData(String findData, List<Map<String, Object>> data, String... columns) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            for (String col : columns) {
                if (data.get(i).get(col) != null && data.get(i).get(col).toString().toLowerCase().contains(findData)) {
                    dataList.add(data.get(i));
                    break;
                }
            }
        }
        return dataList;
    }
}
