package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Children {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    StringProperty birthdate = new SimpleStringProperty();
    StringProperty gender = new SimpleStringProperty();
    StringProperty snils = new SimpleStringProperty();
    StringProperty passportNum = new SimpleStringProperty();
    StringProperty passportSer = new SimpleStringProperty();
    StringProperty registrationDate = new SimpleStringProperty();
    StringProperty educationGroup = new SimpleStringProperty();
    StringProperty status = new SimpleStringProperty();

    public Children() {}

    // Добавьте этот конструктор в класс Children
    public Children(Object id, Object secondName, Object firstName, Object patronymic,
                    Object birthdate, Object gender, Object snils, Object passportNum,
                    Object passportSer, Object registrationDate, Object educationGroup,
                    Object status) {
        this.id = new SimpleIntegerProperty(id != null ? (Integer) id : -1);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName.toString() : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName.toString() : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic.toString() : "");
        this.birthdate = new SimpleStringProperty(birthdate != null ? birthdate.toString() : "");
        this.gender = new SimpleStringProperty(gender != null ? gender.toString() : "");
        this.snils = new SimpleStringProperty(snils != null ? snils.toString() : "");
        this.passportNum = new SimpleStringProperty(passportNum != null ? passportNum.toString() : "");
        this.passportSer = new SimpleStringProperty(passportSer != null ? passportSer.toString() : "");
        this.registrationDate = new SimpleStringProperty(registrationDate != null ? registrationDate.toString() : "");
        this.educationGroup = new SimpleStringProperty(educationGroup != null ? educationGroup.toString() : "");
        this.status = new SimpleStringProperty(status != null ? status.toString() : "");
    }

    public Children(IntegerProperty id, StringProperty secondName, StringProperty firstName, StringProperty patronymic, StringProperty birthdate, StringProperty gender, StringProperty snils, StringProperty passportNum, StringProperty passportSer, StringProperty registrationDate, StringProperty educationGroup, StringProperty status) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
        this.snils = snils;
        this.passportNum = passportNum;
        this.passportSer = passportSer;
        this.registrationDate = registrationDate;
        this.educationGroup = educationGroup;
        this.status = status;
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

    public String getBirthdate() {
        return birthdate.get();
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getSnils() {
        return snils.get();
    }

    public StringProperty snilsProperty() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils.set(snils);
    }

    public String getPassportNum() {
        return passportNum.get();
    }

    public StringProperty passportNumProperty() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum.set(passportNum);
    }

    public String getPassportSer() {
        return passportSer.get();
    }

    public StringProperty passportSerProperty() {
        return passportSer;
    }

    public void setPassportSer(String passportSer) {
        this.passportSer.set(passportSer);
    }

    public String getRegistrationDate() {
        return registrationDate.get();
    }

    public StringProperty registrationDateProperty() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }

    public String getEducationGroup() {
        return educationGroup.get();
    }

    public StringProperty educationGroupProperty() {
        return educationGroup;
    }

    public void setEducationGroup(String educationGroup) {
        this.educationGroup.set(educationGroup);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


}
