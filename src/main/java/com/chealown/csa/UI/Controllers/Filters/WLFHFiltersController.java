package com.chealown.csa.UI.Controllers.Filters;

import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Controllers.FiltersController;
import com.chealown.csa.UI.Controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class WLFHFiltersController implements FiltersController {

    @FXML
    private TextField endDateTF;

    @FXML
    private TextField endGetDateTF;

    @FXML
    private TextField startDateTF;

    @FXML
    private TextField startGetDateTF;
    MainController controller = StaticObjects.getController();

    @FXML
    private void initialize() {
        MaskUtil.applyDateMask(startDateTF);
        MaskUtil.applyDateMask(endDateTF);
        MaskUtil.applyDateMask(startGetDateTF);
        MaskUtil.applyDateMask(endGetDateTF);

        startDateTF.textProperty().addListener(lst -> {
            try {
                controller.applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        startGetDateTF.textProperty().addListener(lst -> {
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
        endGetDateTF.textProperty().addListener(lst -> {
            try {
                controller.applySearchAndFilters();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

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
