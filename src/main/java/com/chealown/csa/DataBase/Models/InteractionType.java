package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InteractionType {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty interactionName = new SimpleStringProperty();

    public InteractionType() {
    }

    public InteractionType(int id, String interactionName) {
        this.id = new SimpleIntegerProperty(id);
        this.interactionName = new SimpleStringProperty(interactionName != null ? interactionName : "");
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

    public String getInteractionName() {
        return interactionName.get();
    }

    public StringProperty interactionNameProperty() {
        return interactionName;
    }

    public void setInteractionName(String interactionName) {
        this.interactionName.set(interactionName);
    }
}
