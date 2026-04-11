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

    public SocialPassport(Object id, Object secondName, Object firstName, Object patronymic,
                          Object education, Object healthGroup, Object familySituation,
                          Object havingADisability, Object dateCreate, Object idChildren) {
        this.id = new SimpleIntegerProperty(id != null ? (Integer) id : -1);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName.toString() : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName.toString() : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic.toString() : "");
        this.education = new SimpleStringProperty(education != null ? education.toString() : "");
        this.healthGroup = new SimpleStringProperty(healthGroup != null ? healthGroup.toString() : "");
        this.familySituation = new SimpleStringProperty(familySituation != null ? familySituation.toString() : "");
        this.havingADisability = new SimpleStringProperty(havingADisability != null ? havingADisability.toString() : "");
        this.dateCreate = new SimpleStringProperty(dateCreate != null ? dateCreate.toString() : "");
        this.idChildren = new SimpleIntegerProperty(idChildren != null ? (Integer) idChildren : -1);
    }

    public SocialPassport(IntegerProperty id, StringProperty secondName, StringProperty firstName, StringProperty patronymic, StringProperty education, StringProperty healthGroup, StringProperty familySituation, StringProperty havingADisability, StringProperty dateCreate) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.education = education;
        this.healthGroup = healthGroup;
        this.familySituation = familySituation;
        this.havingADisability = havingADisability;
        this.dateCreate = dateCreate;
    }

    public SocialPassport(IntegerProperty id, StringProperty education, StringProperty healthGroup, StringProperty familySituation, StringProperty havingADisability, StringProperty dateCreate, IntegerProperty idChildren) {
        this.id = id;
        this.education = education;
        this.healthGroup = healthGroup;
        this.familySituation = familySituation;
        this.havingADisability = havingADisability;
        this.dateCreate = dateCreate;
        this.idChildren = idChildren;
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
