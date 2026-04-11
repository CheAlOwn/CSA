package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SocialMonitoring {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondNameChildren = new SimpleStringProperty();
    StringProperty firstNameChildren = new SimpleStringProperty();
    StringProperty patronymicChildren = new SimpleStringProperty();
    StringProperty dateOfFixation = new SimpleStringProperty();
    StringProperty monitoringType = new SimpleStringProperty();
    StringProperty oldValue = new SimpleStringProperty();
    StringProperty newValue = new SimpleStringProperty();
    StringProperty changeReason = new SimpleStringProperty();
    StringProperty secondNameUser = new SimpleStringProperty();
    StringProperty firstNameUser = new SimpleStringProperty();
    StringProperty patronymicUser = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();
    IntegerProperty idUser = new SimpleIntegerProperty();

    public SocialMonitoring(Object id, Object secondNameChildren, Object firstNameChildren, Object patronymicChildren,
                            Object dateOfFixation, Object monitoringType, Object oldValue, Object newValue,
                            Object changeReason, Object secondNameUser, Object firstNameUser, Object patronymicUser,
                            Object idChildren, Object idUser) {
        this.id = new SimpleIntegerProperty(id != null ? (Integer) id : -1);
        this.secondNameChildren = new SimpleStringProperty(secondNameChildren != null ? secondNameChildren.toString() : "");
        this.firstNameChildren = new SimpleStringProperty(firstNameChildren != null ? firstNameChildren.toString() : "");
        this.patronymicChildren = new SimpleStringProperty(patronymicChildren != null ? patronymicChildren.toString() : "");
        this.dateOfFixation = new SimpleStringProperty(dateOfFixation != null ? dateOfFixation.toString() : "");
        this.monitoringType = new SimpleStringProperty(monitoringType != null ? monitoringType.toString() : "");
        this.oldValue = new SimpleStringProperty(oldValue != null ? oldValue.toString() : "");
        this.newValue = new SimpleStringProperty(newValue != null ? newValue.toString() : "");
        this.changeReason = new SimpleStringProperty(changeReason != null ? changeReason.toString() : "");
        this.secondNameUser = new SimpleStringProperty(secondNameUser != null ? secondNameUser.toString() : "");
        this.firstNameUser = new SimpleStringProperty(firstNameUser != null ? firstNameUser.toString() : "");
        this.patronymicUser = new SimpleStringProperty(patronymicUser != null ? patronymicUser.toString() : "");
        this.idChildren = new SimpleIntegerProperty(idChildren != null ? (Integer) idChildren : -1);
        this.idUser = new SimpleIntegerProperty(idUser != null ? (Integer) idUser : -1);
    }

    public SocialMonitoring(IntegerProperty id, StringProperty secondNameChildren, StringProperty firstNameChildren, StringProperty patronymicChildren, StringProperty dateOfFixation, StringProperty monitoringType, StringProperty oldValue, StringProperty newValue, StringProperty changeReason, StringProperty secondNameUser, StringProperty firstNameUser, StringProperty patronymicUser) {
        this.id = id;
        this.secondNameChildren = secondNameChildren;
        this.firstNameChildren = firstNameChildren;
        this.patronymicChildren = patronymicChildren;
        this.dateOfFixation = dateOfFixation;
        this.monitoringType = monitoringType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeReason = changeReason;
        this.secondNameUser = secondNameUser;
        this.firstNameUser = firstNameUser;
        this.patronymicUser = patronymicUser;
    }

    public SocialMonitoring(IntegerProperty id, StringProperty dateOfFixation, StringProperty monitoringType, StringProperty oldValue, StringProperty newValue, StringProperty changeReason, IntegerProperty idChildren, IntegerProperty idUser) {
        this.id = id;
        this.dateOfFixation = dateOfFixation;
        this.monitoringType = monitoringType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeReason = changeReason;
        this.idChildren = idChildren;
        this.idUser = idUser;
    }

    public SocialMonitoring() {

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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSecondNameChildren() {
        return secondNameChildren.get();
    }

    public StringProperty secondNameChildrenProperty() {
        return secondNameChildren;
    }

    public void setSecondNameChildren(String secondNameChildren) {
        this.secondNameChildren.set(secondNameChildren);
    }

    public String getFirstNameChildren() {
        return firstNameChildren.get();
    }

    public StringProperty firstNameChildrenProperty() {
        return firstNameChildren;
    }

    public void setFirstNameChildren(String firstNameChildren) {
        this.firstNameChildren.set(firstNameChildren);
    }

    public String getPatronymicChildren() {
        return patronymicChildren.get();
    }

    public StringProperty patronymicChildrenProperty() {
        return patronymicChildren;
    }

    public void setPatronymicChildren(String patronymicChildren) {
        this.patronymicChildren.set(patronymicChildren);
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

    public String getSecondNameUser() {
        return secondNameUser.get();
    }

    public StringProperty secondNameUserProperty() {
        return secondNameUser;
    }

    public void setSecondNameUser(String secondNameUser) {
        this.secondNameUser.set(secondNameUser);
    }

    public String getFirstNameUser() {
        return firstNameUser.get();
    }

    public StringProperty firstNameUserProperty() {
        return firstNameUser;
    }

    public void setFirstNameUser(String firstNameUser) {
        this.firstNameUser.set(firstNameUser);
    }

    public String getPatronymicUser() {
        return patronymicUser.get();
    }

    public StringProperty patronymicUserProperty() {
        return patronymicUser;
    }

    public void setPatronymicUser(String patronymicUser) {
        this.patronymicUser.set(patronymicUser);
    }
}
