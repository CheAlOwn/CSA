package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrganizationType {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty typeName = new SimpleStringProperty();

    public OrganizationType(int id, String typeName) {
        this.id = new SimpleIntegerProperty(id);
        this.typeName = new SimpleStringProperty(typeName != null ? typeName : "");
    }

    public OrganizationType() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTypeName() {
        return typeName.get();
    }

    public StringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }
}
