package com.chealown.csa.DataBase.Repositories;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.DataBase.Models.WaitingListForHousing;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;

import javax.crypto.SecretKey;

public class WLFHRepository {
    private static final SecretKey ENCRYPTION_KEY = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    private static final String[] ENCRYPTED_FIELDS = {
    };

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

    public static String[] getEncryptedFields() {
        return ENCRYPTED_FIELDS;
    }
}
