package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Repositories.ChildrenRepository;
import com.chealown.csa.DataBase.Repositories.EmployeeRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class EmployeeTableModule extends TableModule<Employee> {

    public EmployeeTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Employee> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<Employee, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<Employee, String> emailCol = new TableColumn<>("Электронная почта");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Employee, String> phoneCol = new TableColumn<>("Номер телефона");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Employee, String> postCol = new TableColumn<>("Должность");
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));

        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                emailCol,
                phoneCol,
                postCol
        );
    }

    @Override
    protected Task<ObservableList<Employee>> createLoadTask() {
        return new Task<ObservableList<Employee>>() {
            @Override
            protected ObservableList<Employee> call() throws Exception {
                List<Employee> list = EmployeeRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
