package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.DocumentField;
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

    public void setValueTF(DocumentField field) {
        fieldTF.setText(field.getValue());
    }

    public String getValueTF() {
        return fieldTF.getText();
    }

    public TextField getFieldTF() {
        return fieldTF;
    }
}
