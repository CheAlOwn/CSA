package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Interaction {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty organization = new SimpleStringProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    StringProperty interactionDate = new SimpleStringProperty();
    StringProperty interactionType = new SimpleStringProperty();
    StringProperty interactionResult = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();
    IntegerProperty idUser = new SimpleIntegerProperty();

    public Interaction() {}

    public Interaction(Object id, Object organization, Object secondName, Object firstName, Object patronymic, Object interactionDate, Object interactionType, Object interactionResult, Object idChildren) {
        this.id = new SimpleIntegerProperty(id != null ? Integer.parseInt(String.valueOf(id)) : -1);
        this.organization = new SimpleStringProperty(organization != null ? organization.toString() : "");
        this.secondName = new SimpleStringProperty(secondName != null ? secondName.toString() : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName.toString() : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic.toString() : "");
        this.interactionDate = new SimpleStringProperty(interactionDate != null ? interactionDate.toString() : "");
        this.interactionType = new SimpleStringProperty(interactionType != null ? interactionType.toString() : "");
        this.interactionResult = new SimpleStringProperty(interactionResult != null ? interactionResult.toString() : "");
        this.idChildren = new SimpleIntegerProperty(idChildren != null ? (Integer) idChildren : -1);
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

    public String getOrganization() {
        return organization.get();
    }

    public StringProperty organizationProperty() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization.set(organization);
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

    public int getIdChildren() {
        return idChildren.get();
    }

    public IntegerProperty idChildrenProperty() {
        return idChildren;
    }

    public void setIdChildren(int idChildren) {
        this.idChildren.set(idChildren);
    }

    public int getIdUser() {
        return idUser.get();
    }

    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }
}
