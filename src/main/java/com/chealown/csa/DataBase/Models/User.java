package com.chealown.csa.DataBase.Models;

public class User {
    int id;

    // TODO: логин и пароль можно убрать
    String login;
    String secondName;
    String firstName;
    String patronymic;
    String post;

    public User(int id, String login, String secondName, String firstName, String patronymic, String post) {
        this.id = id;
        this.login = login;

        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
