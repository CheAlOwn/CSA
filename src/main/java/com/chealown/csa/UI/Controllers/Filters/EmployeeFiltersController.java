package com.chealown.csa.UI.Controllers.Filters;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Controllers.FiltersController;
import com.chealown.csa.UI.Controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeFiltersController implements FiltersController {

    @FXML
    private ComboBox<String> postCB;

    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();
    }

    private void fillComboBoxes() throws SQLException {
        postCB.getItems().add("Все");
        postCB.getSelectionModel().select("Все");
        ResultSet rs = DBConnector.query("""
                SELECT post_name FROM post""");
        while (rs.next())
            postCB.getItems().add(rs.getString("post_name"));
    }

    @FXML
    void apply(ActionEvent event) throws SQLException {
        controller.applySearchAndFilters();
    }

    public String[] getFilterDataList() {
        return new String[]{postCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[0];
    }

    public String[] getEndDateList() {
        return new String[0];
    }

    public void clearFilters() {
        postCB.getSelectionModel().select("Все");
    }

}
