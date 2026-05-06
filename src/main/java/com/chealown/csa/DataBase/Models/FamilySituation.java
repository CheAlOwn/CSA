package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FamilySituation {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty situationName = new SimpleStringProperty();

    public FamilySituation(int id, String situationName) {
        this.id = new SimpleIntegerProperty(id);
        this.situationName = new SimpleStringProperty(situationName != null ? situationName : "");
    }
    public FamilySituation(String situationName) {
        this.situationName = new SimpleStringProperty(situationName != null ? situationName : "");
    }

    public FamilySituation() {

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

    public String getSituationName() {
        return situationName.get();
    }

    public StringProperty situationNameProperty() {
        return situationName;
    }

    public void setSituationName(String situationName) {
        this.situationName.set(situationName);
    }
}
