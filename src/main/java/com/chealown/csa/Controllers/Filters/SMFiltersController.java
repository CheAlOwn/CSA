package com.chealown.csa.Controllers.Filters;

import com.chealown.csa.Controllers.FiltersController;
import com.chealown.csa.Controllers.MainController;
import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SMFiltersController implements FiltersController {

    @FXML
    private TextField endDateTF;

    @FXML
    private ComboBox<String> monitoringTypeCB;

    @FXML
    private TextField startDateTF;

    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();

        monitoringTypeCB.setOnAction(actionEvent -> {
            controller.applySearchAndFilters();
        });

        startDateTF.textProperty().addListener(lst -> {
            controller.applySearchAndFilters();
        });

        endDateTF.textProperty().addListener(lst -> {
            controller.applySearchAndFilters();
        });
    }

    private void fillComboBoxes() throws SQLException {
        monitoringTypeCB.getItems().add("Все");
        ResultSet rs = DBConnector.query("""
                SELECT monitoring_name FROM monitoring_type""");
        while (rs.next())
            monitoringTypeCB.getItems().add(rs.getString("monitoring_name"));
    }

    public String[] getFilterDataList() {
        return new String[]{monitoringTypeCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText()};
    }

    public void clearFilters() {
        monitoringTypeCB.getItems().add("Все");
        startDateTF.clear();
        endDateTF.clear();
    }
}
