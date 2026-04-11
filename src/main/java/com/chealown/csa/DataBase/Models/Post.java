package com.chealown.csa.DataBase.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Post {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty postName = new SimpleStringProperty();

    public Post(IntegerProperty id, StringProperty postName) {
        this.id = id;
        this.postName = postName;
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

    public String getPostName() {
        return postName.get();
    }

    public StringProperty postNameProperty() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName.set(postName);
    }
}
