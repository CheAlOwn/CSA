package com.chealown.csa.DataBase.Models;

import javafx.beans.property.*;

public class HousingRights {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    StringProperty availabilityOfHousing = new SimpleStringProperty();
    StringProperty formOfOwnership = new SimpleStringProperty();
    StringProperty registrationDate = new SimpleStringProperty();
    StringProperty city = new SimpleStringProperty();
    StringProperty street = new SimpleStringProperty();
    StringProperty build = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();

    public HousingRights() {}

    public HousingRights(int id, String secondName, String firstName, String patronymic,
                         String availabilityOfHousing, String formOfOwnership, String registrationDate,
                         String city, String street, String build, int idChildren) {
        this.id = new SimpleIntegerProperty(id);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic : "");
        this.availabilityOfHousing = new SimpleStringProperty(availabilityOfHousing != null ? availabilityOfHousing : "");
        this.formOfOwnership = new SimpleStringProperty(formOfOwnership != null ? formOfOwnership : "");
        this.registrationDate = new SimpleStringProperty(registrationDate != null ? registrationDate : "");
        this.city = new SimpleStringProperty(city != null ? city : "");
        this.street = new SimpleStringProperty(street != null ? street : "");
        this.build = new SimpleStringProperty(build != null ? build : "");
        this.idChildren = new SimpleIntegerProperty(idChildren);
    }

    public HousingRights(IntegerProperty id, StringProperty secondName, StringProperty firstName, StringProperty patronymic, StringProperty availabilityOfHousing, StringProperty formOfOwnership, StringProperty registrationDate, StringProperty city, StringProperty street, StringProperty build) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.availabilityOfHousing = availabilityOfHousing;
        this.formOfOwnership = formOfOwnership;
        this.registrationDate = registrationDate;
        this.city = city;
        this.street = street;
        this.build = build;
    }

    public HousingRights(IntegerProperty id, StringProperty availabilityOfHousing, StringProperty formOfOwnership, StringProperty registrationDate, IntegerProperty idChildren, StringProperty build, StringProperty street, StringProperty city) {
        this.id = id;
        this.availabilityOfHousing = availabilityOfHousing;
        this.formOfOwnership = formOfOwnership;
        this.registrationDate = registrationDate;
        this.idChildren = idChildren;
        this.build = build;
        this.street = street;
        this.city = city;
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

    public String getAvailabilityOfHousing() {
        return availabilityOfHousing.get();
    }

    public StringProperty availabilityOfHousingProperty() {
        return availabilityOfHousing;
    }

    public void setAvailabilityOfHousing(String availabilityOfHousing) {
        this.availabilityOfHousing.set(availabilityOfHousing);
    }

    public String getFormOfOwnership() {
        return formOfOwnership.get();
    }

    public StringProperty formOfOwnershipProperty() {
        return formOfOwnership;
    }

    public void setFormOfOwnership(String formOfOwnership) {
        this.formOfOwnership.set(formOfOwnership);
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

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getBuild() {
        return build.get();
    }

    public StringProperty buildProperty() {
        return build;
    }

    public void setBuild(String build) {
        this.build.set(build);
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
