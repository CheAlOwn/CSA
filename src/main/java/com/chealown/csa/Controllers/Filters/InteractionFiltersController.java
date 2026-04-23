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

public class InteractionFiltersController implements FiltersController {

    @FXML
    private TextField endDateTF;

    @FXML
    private ComboBox<String> interactionTypeCB;

    @FXML
    private TextField startDateTF;

    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();

        interactionTypeCB.setOnAction(actionEvent -> {
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
        interactionTypeCB.getItems().add("Все");
        ResultSet intRs = DBConnector.query("""
                SELECT interaction_name FROM interaction_type""");
        while (intRs.next())
            interactionTypeCB.getItems().add(intRs.getString("interaction_name"));
    }

    public String[] getFilterDataList() {
        return new String[]{interactionTypeCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText()};
    }
    public void clearFilters() {
        interactionTypeCB.getSelectionModel().select("Все");
        startDateTF.clear();
        endDateTF.clear();
    }

}
