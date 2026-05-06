package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.DataBase.Repositories.EmployeeRepository;
import com.chealown.csa.DataBase.Repositories.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class UserTableModule extends TableModule<User> {

    public UserTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<User> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> loginCol = new TableColumn<>("Логин");
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<User, String> passwordCol = new TableColumn<>("Пароль");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> employeeCol = new TableColumn<>("ID работника");
        employeeCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TableColumn<User, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<User, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<User, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        table.getColumns().addAll(
                idCol,
                loginCol,
                passwordCol,
                employeeCol,
                secondNameCol,
                firstNameCol,
                patronymicCol
        );
    }

    @Override
    protected Task<ObservableList<User>> createLoadTask() {
        return new Task<ObservableList<User>>() {
            @Override
            protected ObservableList<User> call() throws Exception {
                List<User> list = UserRepository.getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
