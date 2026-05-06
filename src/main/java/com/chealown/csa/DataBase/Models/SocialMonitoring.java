package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SocialMonitoring {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic= new SimpleStringProperty();
    StringProperty dateOfFixation = new SimpleStringProperty();
    StringProperty monitoringType = new SimpleStringProperty();
    StringProperty oldValue = new SimpleStringProperty();
    StringProperty newValue = new SimpleStringProperty();
    StringProperty changeReason = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();
    IntegerProperty idUser = new SimpleIntegerProperty();

    public SocialMonitoring(int id, String secondName, String firstName, String patronymic,
                            String dateOfFixation, String monitoringType, String oldValue, String newValue,
                            String changeReason, int idChildren) {
        this.id = new SimpleIntegerProperty(id);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic : "");
        this.dateOfFixation = new SimpleStringProperty(dateOfFixation != null ? dateOfFixation : "");
        this.monitoringType = new SimpleStringProperty(monitoringType != null ? monitoringType : "");
        this.oldValue = new SimpleStringProperty(oldValue != null ? oldValue : "");
        this.newValue = new SimpleStringProperty(newValue != null ? newValue : "");
        this.changeReason = new SimpleStringProperty(changeReason != null ? changeReason : "");
        this.idChildren = new SimpleIntegerProperty(id);
    }

    public SocialMonitoring() {}

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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDateOfFixation() {
        return dateOfFixation.get();
    }

    public StringProperty dateOfFixationProperty() {
        return dateOfFixation;
    }

    public void setDateOfFixation(String dateOfFixation) {
        this.dateOfFixation.set(dateOfFixation);
    }

    public String getMonitoringType() {
        return monitoringType.get();
    }

    public StringProperty monitoringTypeProperty() {
        return monitoringType;
    }

    public void setMonitoringType(String monitoringType) {
        this.monitoringType.set(monitoringType);
    }

    public String getOldValue() {
        return oldValue.get();
    }

    public StringProperty oldValueProperty() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue.set(oldValue);
    }

    public String getNewValue() {
        return newValue.get();
    }

    public StringProperty newValueProperty() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue.set(newValue);
    }

    public String getChangeReason() {
        return changeReason.get();
    }

    public StringProperty changeReasonProperty() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason.set(changeReason);
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
