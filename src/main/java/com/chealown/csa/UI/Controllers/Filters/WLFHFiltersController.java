package com.chealown.csa.UI.Controllers.Filters;

import com.chealown.csa.UI.Controllers.FiltersController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WLFHFiltersController implements FiltersController {

    @FXML
    private TextField endDateTF;

    @FXML
    private TextField endGetDateTF;

    @FXML
    private TextField startDateTF;

    @FXML
    private TextField startGetDateTF;

    public String[] getFilterDataList() {
        return new String[0];
    }

    public String[] getStartDateList() {
        return new String[]{startDateTF.getText(), startGetDateTF.getText()};
    }

    public String[] getEndDateList() {
        return new String[]{endDateTF.getText(), endGetDateTF.getText()};
    }

    public void clearFilters() {
        startDateTF.clear();
        startGetDateTF.clear();
        endDateTF.clear();
        endGetDateTF.clear();
    }
}
