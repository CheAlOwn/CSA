package com.chealown.csa.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.TemplateField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FieldTemplateController {

    @FXML
    private Label fieldNameLB;

    @FXML
    private TextField fieldTF;

    public void setLabel(TemplateField field) {
        fieldNameLB.setText(field.getLabel());
    }

}
