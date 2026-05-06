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

public class SPFiltersController implements FiltersController {

    @FXML
    private ComboBox<String> disabilityCB;

    @FXML
    private ComboBox<String> educationLevelCB;

    @FXML
    private TextField endDateTF;

    @FXML
    private ComboBox<String> familySituationCB;

    @FXML
    private ComboBox<String> healthGroupCB;

    @FXML
    private TextField startDateTF;

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

        disabilityCB.setOnAction(actionEvent -> {
            try {
                controller.applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void fillComboBoxes() throws SQLException {
        educationLevelCB.getItems().add("Все");
        familySituationCB.getItems().add("Все");
        healthGroupCB.getItems().add("Все");
        disabilityCB.getItems().addAll("Все", "Да", "Нет");

        ResultSet eduRs = DBConnector.query("""
                SELECT education_name FROM education_level""");
        while (eduRs.next())
            educationLevelCB.getItems().add(SecurityUtil.decryptSafe(eduRs.getString("education_name"), key));

        ResultSet famRs = DBConnector.query("""
                SELECT situation_name FROM family_situation""");
        while(famRs.next())
            familySituationCB.getItems().add(SecurityUtil.decryptSafe(famRs.getString("situation_name"), key));

        ResultSet heaRs = DBConnector.query("""
                SELECT group_name FROM health_group""");
        while (heaRs.next())
            healthGroupCB.getItems().add(SecurityUtil.decryptSafe(heaRs.getString("group_name"), key));
    }

    @FXML
    private void apply(ActionEvent actionEvent) throws SQLException {
        controller.applySearchAndFilters();
    }

    public String[] getFilterDataList() {

        return new String[]{educationLevelCB.getSelectionModel().getSelectedItem(), healthGroupCB.getSelectionModel().getSelectedItem(), familySituationCB.getSelectionModel().getSelectedItem(), disabilityCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText()};
    }

    public void clearFilters() {
        educationLevelCB.getSelectionModel().select("Все");
        healthGroupCB.getSelectionModel().select("Все");
        familySituationCB.getSelectionModel().select("Все");
        disabilityCB.getSelectionModel().select("Все");
        startDateTF.clear();
        endDateTF.clear();
    }
}
