package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Interaction;
import com.chealown.csa.Entities.StaticObjects;

public class InteractionRepository {
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка", "Название организации",
            "Тип взаимодействия", "Дата взаимодействия", "Результат взаимодействия"
    };
    private static final String QUERY = """
                        SELECT i.id, c.second_name, c.first_name, c.patronymic, c.id, o.organization_name, 
                        it.interaction_name, interaction_date, interaction_result
                        FROM interaction i
                        INNER JOIN children_interaction ci ON i.id = ci.id_interaction
                        INNER JOIN children c ON ci.id_children = c.id
                        INNER JOIN organization o ON id_organization = o.id
                        INNER JOIN interaction_type it ON interaction_type = it.id
                        WHERE i.archive = false
                        """;


    public static boolean save(Interaction interaction) {
        if (StaticObjects.getInteraction() == null) {
            return insert(interaction);
        } else {
            return update(interaction);
        }
    }

    private static boolean insert(Interaction interaction) {
        String sql = """
                INSERT INTO interaction
                     (id_organization, id_user, interaction_date, interaction_type, interaction_result)
                     VALUES(?, ?, ?, ?, ?)
                     RETURNING id
                """;

        Object[] params = {
                Integer.parseInt(interaction.getOrganization()),
                interaction.getIdUser(),
                interaction.getInteractionDate(),
                Integer.parseInt(interaction.getInteractionType()),
                interaction.getInteractionResult()
        };
        int returnedId = DBConnector.updateWithReturningId(sql, params);

        sql = """
                INSERT INTO children_interaction
                (id_children, id_interaction)
                VALUES(?, ?);
                """;

        params = new Object[]{
                interaction.getIdChildren(),
                returnedId
        };

        int result = DBConnector.update(sql, params);
        return result > 0;
    }


    private static boolean update(Interaction interaction) {
        String sql = """
                UPDATE interaction
                SET id_organization=?, id_user=?, interaction_date=?, 
                interaction_type=?, interaction_result=?
                WHERE id=?;
                """;

        Object[] params = {
                Integer.parseInt(interaction.getOrganization()),
                interaction.getIdUser(),
                interaction.getInteractionDate(),
                Integer.parseInt(interaction.getInteractionType()),
                interaction.getInteractionResult(),
                interaction.getId()
        };

        int result1 = DBConnector.update(sql, params);

        sql = """
                UPDATE children_interaction
                SET id_children=?
                WHERE id_interaction=?;
                """;

        params = new Object[]{
                interaction.getIdChildren(),
                interaction.getId()
        };

        int result2 = DBConnector.update(sql, params);

        return result1 + result1 > 1;
    }

    public static void archive(Interaction interaction) {
        String sql = """
                UPDATE interaction
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                interaction.getId()
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
