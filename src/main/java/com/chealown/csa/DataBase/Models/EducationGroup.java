package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationGroup {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty groupName = new SimpleStringProperty();
    StringProperty tutor = new SimpleStringProperty();

    public EducationGroup(IntegerProperty id, StringProperty groupName, StringProperty tutor) {
        this.id = id;
        this.groupName = groupName;
        this.tutor = tutor;
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

    public String getGroupName() {
        return groupName.get();
    }

    public StringProperty groupNameProperty() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public String getTutor() {
        return tutor.get();
    }

    public StringProperty tutorProperty() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor.set(tutor);
    }
}
