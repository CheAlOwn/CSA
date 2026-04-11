package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationLevel {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty educationName = new SimpleStringProperty();

    public EducationLevel(IntegerProperty id, StringProperty educationName) {
        this.id = id;
        this.educationName = educationName;
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

    public String getEducationName() {
        return educationName.get();
    }

    public StringProperty educationNameProperty() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName.set(educationName);
    }
}
