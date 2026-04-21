package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class HousingRightsRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка",
            "Наличие жилья", "Форма собственности", "Дата регистрации",
            "Город", "Улица", "Здание"
    };
    private static final String QUERY = """
                        SELECT h.id, c.second_name, c.first_name, c.patronymic, c.id,
                               h.availability_of_housing, form_name,
                               h.registration_date, h.city, h.street, h.build
                        FROM housing_rights h
                        INNER JOIN children c ON h.id_children = c.id
                        INNER JOIN ownership_form o ON h.form_of_ownership=o.id
                        WHERE h.archive = false
                        """;


    public static boolean save(HousingRights housingRights) {
        if (StaticObjects.getHousingRights() == null) {
            return insert(housingRights);
        } else {
            return update(housingRights);
        }
    }

    private static boolean insert(HousingRights housingRights) {
        String sql = """
                INSERT INTO housing_rights (
                id_children, availability_of_housing, form_of_ownership,
                registration_date, city, street, build
                )
                VALUES(?, ?, ?, ?, ?, ?, ?);
                """;

        Object[] params = {
                housingRights.getIdChildren(),
                housingRights.getAvailabilityOfHousing(),
                Integer.parseInt(housingRights.getFormOfOwnership()),
                housingRights.getRegistrationDate(),
                SecurityUtil.encryptSafe(housingRights.getCity(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(housingRights.getStreet(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(housingRights.getBuild(), ENCRYPTION_KEY)
        };
        int result = DBConnector.update(sql, params) ;
        return result > 0;
    }


    private static boolean update(HousingRights housingRights) {
        String sql = """
                UPDATE housing_rights
                SET id_children = ?, availability_of_housing = ?, form_of_ownership = ?, 
                registration_date = ?, city = ?, street = ?, build = ?
                WHERE id = ?; 
                """;

        Object[] params = {
                housingRights.getIdChildren(),
                housingRights.getAvailabilityOfHousing(),
                Integer.parseInt(housingRights.getFormOfOwnership()),
                housingRights.getRegistrationDate(),
                SecurityUtil.encryptSafe(housingRights.getCity(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(housingRights.getStreet(), ENCRYPTION_KEY),
                SecurityUtil.encryptSafe(housingRights.getBuild(), ENCRYPTION_KEY),
                housingRights.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void archive(HousingRights housingRights) {
        String sql = """
                UPDATE housing_rights
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                housingRights.getId()
        };

        DBConnector.update(sql, params);
    }

    public static String[] getDisplayColumns() {
        return DISPLAY_COLUMNS;
    }

    public static String getQUERY() {
        return QUERY;
    }
}
