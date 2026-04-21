package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WaitingListForHousing {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty secondName = new SimpleStringProperty();
    StringProperty firstName = new SimpleStringProperty();
    StringProperty patronymic = new SimpleStringProperty();
    IntegerProperty numberInTheQueue = new SimpleIntegerProperty();
    StringProperty dateAdded = new SimpleStringProperty();
    StringProperty expectedDateOfIssue = new SimpleStringProperty();
    StringProperty currentStep = new SimpleStringProperty();
    IntegerProperty idChildren = new SimpleIntegerProperty();

    public WaitingListForHousing(Object id, Object secondName, Object firstName, Object patronymic,
                                 Object numberInTheQueue, Object dateAdded, Object expectedDateOfIssue,
                                 Object currentStep, Object idChildren) {
        this.id = new SimpleIntegerProperty(id != null ? (Integer) id : -1);
        this.secondName = new SimpleStringProperty(secondName != null ? secondName.toString() : "");
        this.firstName = new SimpleStringProperty(firstName != null ? firstName.toString() : "");
        this.patronymic = new SimpleStringProperty(patronymic != null ? patronymic.toString() : "");
        this.numberInTheQueue = new SimpleIntegerProperty(numberInTheQueue != null ? Integer.parseInt(numberInTheQueue.toString()) : 0);
        this.dateAdded = new SimpleStringProperty(dateAdded != null ? dateAdded.toString() : "");
        this.expectedDateOfIssue = new SimpleStringProperty(expectedDateOfIssue != null ? expectedDateOfIssue.toString() : "");
        this.currentStep = new SimpleStringProperty(currentStep != null ? currentStep.toString() : "");
        this.idChildren = new SimpleIntegerProperty(idChildren != null ? (Integer) idChildren : -1);
    }

    public WaitingListForHousing() {}

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

    public int getNumberInTheQueue() {
        return numberInTheQueue.get();
    }

    public IntegerProperty numberInTheQueueProperty() {
        return numberInTheQueue;
    }

    public void setNumberInTheQueue(int numberInTheQueue) {
        this.numberInTheQueue.set(numberInTheQueue);
    }

    public String getDateAdded() {
        return dateAdded.get();
    }

    public StringProperty dateAddedProperty() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded.set(dateAdded);
    }

    public String getExpectedDateOfIssue() {
        return expectedDateOfIssue.get();
    }

    public StringProperty expectedDateOfIssueProperty() {
        return expectedDateOfIssue;
    }

    public void setExpectedDateOfIssue(String expectedDateOfIssue) {
        this.expectedDateOfIssue.set(expectedDateOfIssue);
    }

    public String getCurrentStep() {
        return currentStep.get();
    }

    public StringProperty currentStepProperty() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep.set(currentStep);
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
