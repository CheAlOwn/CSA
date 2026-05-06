package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Repositories.ChildrenRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ChildrenTableModule extends TableModule<Children> {

    public ChildrenTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Children> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Children, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Children, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<Children, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Children, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<Children, String> birthdateCol = new TableColumn<>("Дата рождения");
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        TableColumn<Children, String> genderCol = new TableColumn<>("Пол");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Children, String> snilsCol = new TableColumn<>("СНИЛС");
        snilsCol.setCellValueFactory(new PropertyValueFactory<>("snils"));

        TableColumn<Children, String> passportSerCol = new TableColumn<>("Серия паспорта");
        passportSerCol.setCellValueFactory(new PropertyValueFactory<>("passportSer"));

        TableColumn<Children, String> passportNumCol = new TableColumn<>("Номер паспорта");
        passportNumCol.setCellValueFactory(new PropertyValueFactory<>("passportNum"));

        TableColumn<Children, String> groupCol = new TableColumn<>("Учебная группа");
        groupCol.setCellValueFactory(new PropertyValueFactory<>("educationGroup"));

        TableColumn<Children, String> statusCol = new TableColumn<>("Статус");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));


        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                birthdateCol,
                genderCol,
                snilsCol,
                passportSerCol,
                passportNumCol,
                groupCol,
                statusCol
        );
    }

    @Override
    protected Task<ObservableList<Children>> createLoadTask() {
        return new Task<ObservableList<Children>>() {
            @Override
            protected ObservableList<Children> call() throws Exception {
                List<Children> list = ChildrenRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }

}
