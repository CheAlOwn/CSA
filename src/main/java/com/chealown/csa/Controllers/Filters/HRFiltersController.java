package com.chealown.csa.Controllers.Filters;

import com.chealown.csa.Controllers.FiltersController;
import com.chealown.csa.Controllers.MainController;
import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class HRFiltersController implements FiltersController {

    @FXML
    private ComboBox<String> availabilityOfHousingCB;

    @FXML
    private ComboBox<String> cityCB;

    @FXML
    private TextField endDateTF;

    @FXML
    private ComboBox<String> formOfOwnershipCB;

    @FXML
    private TextField startDateTF;
    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();
        startDateTF.textProperty().addListener(lst -> {
            controller.applySearchAndFilters();
        });

        endDateTF.textProperty().addListener(lst -> {
            controller.applySearchAndFilters();
        });

        availabilityOfHousingCB.setOnAction(actionEvent -> {
            controller.applySearchAndFilters();
        });
    }

    @FXML
    private void apply(ActionEvent actionEvent) {
        controller.applySearchAndFilters();
    }

    private void fillComboBoxes() throws SQLException {
        availabilityOfHousingCB.getItems().addAll("Все","Да", "Нет");
        formOfOwnershipCB.getItems().add("Все");
        cityCB.getItems().add("Все");
        ResultSet rs = DBConnector.query("""
                SELECT form_name FROM ownership_form""");
        while (rs.next())
            formOfOwnershipCB.getItems().add(rs.getString("form_name"));

        Set<String> cities = new LinkedHashSet<>();
        ResultSet cityRs = DBConnector.query("""
                SELECT city FROM housing_rights WHERE city is not null""");
        while (cityRs.next())
            cities.add(SecurityUtil.decryptSafe(cityRs.getString("city"), SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY")));
        cityCB.getItems().addAll(cities);
    }

    public String[] getFilterDataList() {
        return new String[]{availabilityOfHousingCB.getSelectionModel().getSelectedItem(), formOfOwnershipCB.getSelectionModel().getSelectedItem(), cityCB.getSelectionModel().getSelectedItem()};
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText()};
    }

    public void clearFilters() {
        formOfOwnershipCB.getSelectionModel().select("Все");
        cityCB.getSelectionModel().select("Все");
        availabilityOfHousingCB.getSelectionModel().select("Все");
        startDateTF.clear();
        endDateTF.clear();
    }
}
