package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Interaction {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty organizationName = new SimpleStringProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    StringProperty interactionDate = new SimpleStringProperty();
    StringProperty interactionType = new SimpleStringProperty();
    StringProperty interactionResult = new SimpleStringProperty();

    public Interaction(IntegerProperty id, StringProperty organizationName, StringProperty secondName, StringProperty firstName, StringProperty patronymic, StringProperty interactionDate, StringProperty interactionType, StringProperty interactionResult) {
        this.id = id;
        this.organizationName = organizationName;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.interactionDate = interactionDate;
        this.interactionType = interactionType;
        this.interactionResult = interactionResult;
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

    public String getOrganizationName() {
        return organizationName.get();
    }

    public StringProperty organizationNameProperty() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName.set(organizationName);
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

    public String getInteractionDate() {
        return interactionDate.get();
    }

    public StringProperty interactionDateProperty() {
        return interactionDate;
    }

    public void setInteractionDate(String interactionDate) {
        this.interactionDate.set(interactionDate);
    }

    public String getInteractionType() {
        return interactionType.get();
    }

    public StringProperty interactionTypeProperty() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType.set(interactionType);
    }

    public String getInteractionResult() {
        return interactionResult.get();
    }

    public StringProperty interactionResultProperty() {
        return interactionResult;
    }

    public void setInteractionResult(String interactionResult) {
        this.interactionResult.set(interactionResult);
    }
}
