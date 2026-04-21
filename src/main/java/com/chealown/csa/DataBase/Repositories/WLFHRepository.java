package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.WaitingListForHousing;
import com.chealown.csa.Entities.StaticObjects;

public class WLFHRepository {
    private static final String[] DISPLAY_COLUMNS = {
            "ID", "Фамилия", "Имя", "Отчество", "ID ребенка", "Номер в очереди",
            "Дата постановки в очередь", "Ожидаемая дата выдачи", "Текущий шаг"
    };
    private static final String QUERY = """
                        SELECT w.id, c.second_name, c.first_name, c.patronymic, c.id,
                               w.number_in_the_queue, w.date_added,
                               w.expected_date_of_issue, w.current_step
                        FROM waiting_list_for_housing w
                        INNER JOIN children c ON w.id_children = c.id
                        WHERE w.archive = false
                        """;


    public static boolean save(WaitingListForHousing wlfh) {
        if (StaticObjects.getListForHousing() == null) {
            System.out.println("insert");
            return insert(wlfh);
        } else {
            System.out.println("update");
            return update(wlfh);
        }
    }

    private static boolean insert(WaitingListForHousing wlfh) {
        String sql = """
                INSERT INTO waiting_list_for_housing (
                id_children, number_in_the_queue, date_added, 
                expected_date_of_issue, current_step
                )
                VALUES(?, ?, ?, ?, ?);
                """;

        Object[] params = {
                wlfh.getIdChildren(),
                wlfh.getNumberInTheQueue(),
                wlfh.getDateAdded(),
                wlfh.getExpectedDateOfIssue(),
                wlfh.getCurrentStep()
        };
        int result = DBConnector.update(sql, params) ;
        return result > 0;
    }



    private static boolean update(WaitingListForHousing wlfh) {
        String sql = """
                UPDATE waiting_list_for_housing
                    SET id_children = ?, number_in_the_queue = ?, date_added = ?, 
                    expected_date_of_issue = ?, current_step = ?
                    WHERE id = ?;
            """;

        Object[] params = {
                wlfh.getIdChildren(),
                wlfh.getNumberInTheQueue(),
                wlfh.getDateAdded(),
                wlfh.getExpectedDateOfIssue(),
                wlfh.getCurrentStep(),
                wlfh.getId()
        };

        return DBConnector.update(sql, params) > 0;
    }

    public static void archive(WaitingListForHousing wlfh) {
        String sql = """
                UPDATE waiting_list_for_housing
                SET archive=true
                WHERE id=?;
                """;

        Object[] params = {
                wlfh.getId()
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
