package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.WaitingListForHousing;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WLFHRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String QUERY = """
                        SELECT w.id, c.second_name, c.first_name, c.patronymic, c.id as id_child,
                               w.number_in_the_queue, w.date_added,
                               w.expected_date_of_issue, w.current_step
                        FROM waiting_list_for_housing w
                        INNER JOIN children c ON w.id_children = c.id
                        WHERE w.archive = false or w.archive = ?
                        """;


    public static boolean save(WaitingListForHousing wlfh) {
        if (StaticObjects.getSelectedObject() == null) {
            System.out.println("insert");
            return insert(wlfh);
        } else {
            System.out.println("update");
            return update(wlfh);
        }
    }

    public static void deleteRecord(WaitingListForHousing wlfh, boolean isAdmin) {
        if (isAdmin) {
            delete(wlfh);
        } else {
            archive(wlfh);
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

    private static void archive(WaitingListForHousing wlfh) {
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

    private static void delete(WaitingListForHousing wlfh) {
        String sql = """
                DELETE FROM waiting_list_for_housing
                WHERE id=?;
                """;

        Object[] params = {
                wlfh.getId()
        };

        DBConnector.update(sql, params);
    }


    public static String getQUERY() {
        return QUERY;
    }

    public static List<WaitingListForHousing> getAllData(boolean withArchive) throws SQLException {
        ArrayList<WaitingListForHousing> data = new ArrayList<>();
        ResultSet rs = DBConnector.query(QUERY, withArchive);
        while (rs.next()) {
            data.add(new WaitingListForHousing(
                    rs.getInt("id"),
                    SecurityUtil.decryptSafe(rs.getString("second_name"), ENCRYPTION_KEY),
                    SecurityUtil.decryptSafe(rs.getString("first_name"), ENCRYPTION_KEY),
                            SecurityUtil.decryptSafe(rs.getString("patronymic"), ENCRYPTION_KEY),
                    rs.getString("number_in_the_queue"),
                    rs.getString("date_added"),
                    rs.getString("expected_date_of_issue"),
                    rs.getString("current_step"),
                    rs.getInt("id_child")
            ));
        }

        return data;
    }
}
