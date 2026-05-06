package com.chealown.csa.UI.Controllers.Filters;

import com.chealown.csa.UI.Controllers.FiltersController;
import com.chealown.csa.UI.Controllers.MainController;
import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.crypto.SecretKey;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChildrenFiltersController implements FiltersController {

    @FXML
    private ComboBox<String> educationGroupCB;

    @FXML
    private TextField endDateTF;

    @FXML
    private ComboBox<String> genderCB;

    @FXML
    private TextField startDateTF;

    @FXML
    private ComboBox<String> statusCB;

    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();
        startDateTF.textProperty().addListener(lst -> {
            try {
                controller.applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        endDateTF.textProperty().addListener(lst -> {
            try {
                controller.applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    private void apply(ActionEvent actionEvent) throws SQLException {
        controller.applySearchAndFilters();
    }

    private void fillComboBoxes() throws SQLException {
        educationGroupCB.getItems().add("Все");
        genderCB.getItems().add("Все");
        statusCB.getItems().add("Все");

        ResultSet eduRs = DBConnector.query("""
                SELECT group_name FROM education_group""");
        while (eduRs.next())
            educationGroupCB.getItems().add(SecurityUtil.decryptSafe(eduRs.getString("group_name"), key));

        ResultSet genRs = DBConnector.query("""
                SELECT gender_name FROM gender""");
        while (genRs.next())
            genderCB.getItems().add(SecurityUtil.decryptSafe(genRs.getString("gender_name"), key));

        ResultSet statRs = DBConnector.query("""
                SELECT status_name FROM status""");
        while (statRs.next())
            statusCB.getItems().add(SecurityUtil.decryptSafe(statRs.getString("status_name"), key));
    }

    public String[] getFilterDataList() {
        return new String[]{genderCB.getSelectionModel().getSelectedItem(), educationGroupCB.getSelectionModel().getSelectedItem(), statusCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText()};
    }

    public void clearFilters() {
        genderCB.getSelectionModel().select("Все");
        educationGroupCB.getSelectionModel().select("Все");
        statusCB.getSelectionModel().select("Все");
        startDateTF.clear();
        endDateTF.clear();
    }
}
