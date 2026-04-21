package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class SocialPassportRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка", "Уровень образования",
            "Группа здоровья", "Семейное положение", "Инвалидность", "Дата создания"
    };
    private static final String QUERY = """
                        SELECT s.id_passport, c.second_name, c.first_name, c.patronymic, c.id,
                               el.education_name, hg.group_name, t.situation_name,
                               s.having_a_disability, s.date_create
                        FROM social_passport s
                        INNER JOIN children c ON s.id_children = c.id
                        INNER JOIN education_level el ON s.id_education = el.id
                        INNER JOIN health_group hg ON s.id_health_group = hg.id
                        INNER JOIN family_situation t ON s.id_family_situation = t.id
                        WHERE s.archive = false
                        """;


    public static boolean save(SocialPassport socialPassport) {
        if (StaticObjects.getSocialPassport() == null) {
            System.out.println("insert");
            return insert(socialPassport);
        } else {
            System.out.println("update");
            return update(socialPassport);
        }
    }

    private static boolean insert(SocialPassport socialPassport) {
        String sql = """
                INSERT INTO social_passport (
                id_children, id_education, id_health_group, 
                id_family_situation, having_a_disability, date_create
                )
                VALUES(?, ?, ?, ?, ?, ?);
                """;

        Object[] params = {
                socialPassport.getIdChildren(),
                socialPassport.getEducation(),
                socialPassport.getHealthGroup(),
                socialPassport.getFamilySituation(),
                SecurityUtil.encryptSafe(socialPassport.getHavingADisability(), ENCRYPTION_KEY),
                socialPassport.getDateCreate()
        };
        int result = DBConnector.update(sql, params) ;
        return result > 0;
    }

    private static boolean update(SocialPassport socialPassport) {
        String sql = """
                UPDATE social_passport
                SET id_children = ?, id_education = ?, id_health_group = ?, 
                id_family_situation = ?, having_a_disability = ?, date_create = ?
                WHERE id_passport = ?;
            """;

        Object[] params = {
                socialPassport.getIdChildren(),
                socialPassport.getEducation(),
                socialPassport.getHealthGroup(),
                socialPassport.getFamilySituation(),
                SecurityUtil.encryptSafe(socialPassport.getHavingADisability(), ENCRYPTION_KEY),
                socialPassport.getDateCreate(),
                socialPassport.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void archive(SocialPassport socialPassport) {
        String sql = """
                UPDATE social_passport
                SET archive=true
                WHERE id_passport=?;
                """;

        Object[] params = {
                socialPassport.getId()
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
