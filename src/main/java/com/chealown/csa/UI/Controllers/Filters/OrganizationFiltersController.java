package com.chealown.csa.UI.Controllers.Filters;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Controllers.FiltersController;
import com.chealown.csa.UI.Controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class OrganizationFiltersController implements FiltersController {

    @FXML
    private ComboBox<String> cityCB;

    @FXML
    private ComboBox<String> typeCB;
    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();
    }

    private void fillComboBoxes() throws SQLException {
        cityCB.getItems().add("Все");
        typeCB.getItems().add("Все");
        cityCB.getSelectionModel().select("Все");
        typeCB.getSelectionModel().select("Все");
        ResultSet rs = DBConnector.query("""
                SELECT type_name FROM organization_type""");
        while (rs.next())
            typeCB.getItems().add(rs.getString("type_name"));

        Set<String> cities = new LinkedHashSet<>();
        ResultSet cityRs = DBConnector.query("""
                SELECT city FROM organization WHERE city is not null""");
        while (cityRs.next())
            cities.add(cityRs.getString("city"));
        cityCB.getItems().addAll(cities);
    }

    @FXML
    void apply(ActionEvent event) throws SQLException {
        controller.applySearchAndFilters();
    }

    public String[] getFilterDataList() {
        return new String[]{cityCB.getSelectionModel().getSelectedItem(), typeCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[0];
    }

    public String[] getEndDateList() {
        return new String[0];
    }

    public void clearFilters() {
        cityCB.getSelectionModel().select("Все");
        typeCB.getSelectionModel().select("Все");
    }

}
