package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EducationGroup {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty groupName = new SimpleStringProperty();
    IntegerProperty tutorId = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();


    public EducationGroup(IntegerProperty id, StringProperty groupName, IntegerProperty tutorId) {
        this.id = id;
        this.groupName = groupName;
        this.tutorId = tutorId;
    }
    public EducationGroup(int id, String groupName, int tutor) {
        this.id = new SimpleIntegerProperty(id);
        this.groupName = new SimpleStringProperty(groupName != null ? groupName : "");
        this.tutorId = new SimpleIntegerProperty(tutor);
    }
    public EducationGroup(String groupName, int tutor) {
        this.groupName = new SimpleStringProperty(groupName != null ? groupName : "");
        this.tutorId = new SimpleIntegerProperty(tutor);
    }

    public EducationGroup(int id, String groupName, int tutor, String secondName, String firstName, String patronymic) {
        this.id = new SimpleIntegerProperty(id);
        this.groupName = new SimpleStringProperty(groupName != null ? groupName : "");
        this.tutorId = new SimpleIntegerProperty(tutor);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic : "");
    }

    public EducationGroup() {

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

    public int getTutorId() {
        return tutorId.get();
    }

    public IntegerProperty tutorIdProperty() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId.set(tutorId);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }
}
