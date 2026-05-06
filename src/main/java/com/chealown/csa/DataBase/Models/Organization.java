package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Organization {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty organizationName = new SimpleStringProperty();
    StringProperty city = new SimpleStringProperty();
    StringProperty street = new SimpleStringProperty();
    StringProperty build = new SimpleStringProperty();
    StringProperty phone = new SimpleStringProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty type = new SimpleStringProperty();

    public Organization(int id, String organizationName, String city, String street, String build, String phone, String email, String type) {
        this.id = new SimpleIntegerProperty(id);
        this.organizationName = new SimpleStringProperty(organizationName != null ? organizationName : "");
        this.city = new SimpleStringProperty(city != null ? city : "");
        this.street = new SimpleStringProperty(street != null ? street : "");
        this.build = new SimpleStringProperty(build != null ? build : "");
        this.phone = new SimpleStringProperty(phone != null ? phone : "");
        this.email = new SimpleStringProperty(email != null ? email : "");
        this.type = new SimpleStringProperty(type != null ? type : "");
    }

    public Organization() {

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

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
