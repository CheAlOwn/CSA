package com.chealown.csa.DataBase.Models;

import javafx.beans.property.*;

public class SocialPassport {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    StringProperty education = new SimpleStringProperty();
    StringProperty healthGroup = new SimpleStringProperty();
    StringProperty familySituation = new SimpleStringProperty();
    StringProperty havingADisability = new SimpleStringProperty();
    StringProperty dateCreate = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();

    public SocialPassport(int id, String secondName, String firstName, String patronymic,
                          String education, String healthGroup, String familySituation,
                          String havingADisability, String dateCreate, int idChildren) {
        this.id = new SimpleIntegerProperty(id);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic : "");
        this.education = new SimpleStringProperty(education != null ? education : "");
        this.healthGroup = new SimpleStringProperty(healthGroup != null ? healthGroup : "");
        this.familySituation = new SimpleStringProperty(familySituation != null ? familySituation : "");
        this.havingADisability = new SimpleStringProperty(havingADisability != null ? havingADisability : "");
        this.dateCreate = new SimpleStringProperty(dateCreate != null ? dateCreate : "");
        this.idChildren = new SimpleIntegerProperty(idChildren);
    }

    public SocialPassport() {

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

    public String getEducation() {
        return education.get();
    }

    public StringProperty educationProperty() {
        return education;
    }

    public void setEducation(String education) {
        this.education.set(education);
    }

    public String getHealthGroup() {
        return healthGroup.get();
    }

    public StringProperty healthGroupProperty() {
        return healthGroup;
    }

    public void setHealthGroup(String healthGroup) {
        this.healthGroup.set(healthGroup);
    }

    public String getFamilySituation() {
        return familySituation.get();
    }

    public StringProperty familySituationProperty() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation.set(familySituation);
    }

    public String getHavingADisability() {
        return havingADisability.get();
    }

    public StringProperty havingADisabilityProperty() {
        return havingADisability;
    }

    public void setHavingADisability(String havingADisability) {
        this.havingADisability.set(havingADisability);
    }

    public String getDateCreate() {
        return dateCreate.get();
    }

    public StringProperty dateCreateProperty() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate.set(dateCreate);
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
}
