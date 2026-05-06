package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.User;
import com.chealown.csa.DataBase.Models.WaitingListForHousing;
import com.chealown.csa.DataBase.Repositories.UserRepository;
import com.chealown.csa.DataBase.Repositories.WLFHRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class WLFHTableModule extends TableModule<WaitingListForHousing> {

    public WLFHTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<WaitingListForHousing> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {

        TableColumn<WaitingListForHousing, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<WaitingListForHousing, String> childIdCol = new TableColumn<>("ID ребенка");
        childIdCol.setCellValueFactory(new PropertyValueFactory<>("idChildren"));

        TableColumn<WaitingListForHousing, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<WaitingListForHousing, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<WaitingListForHousing, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<WaitingListForHousing, String> queueCol = new TableColumn<>("Номер в очереди");
        queueCol.setCellValueFactory(new PropertyValueFactory<>("numberInTheQueue"));

        TableColumn<WaitingListForHousing, String>  dateAddedCol = new TableColumn<>("Дата постановки в очередь");
        dateAddedCol.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));

        TableColumn<WaitingListForHousing, String> expectedDateOfIssueCol = new TableColumn<>("Ожидаемая дата выдачи");
        expectedDateOfIssueCol.setCellValueFactory(new PropertyValueFactory<>("expectedDateOfIssue"));

        TableColumn<WaitingListForHousing, String> currentStepCol = new TableColumn<>("Текущий шаг");
        currentStepCol.setCellValueFactory(new PropertyValueFactory<>("currentStep"));

        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                childIdCol,
                queueCol,
                dateAddedCol,
                expectedDateOfIssueCol,
                currentStepCol
        );
    }

    @Override
    protected Task<ObservableList<WaitingListForHousing>> createLoadTask() {
        return new Task<ObservableList<WaitingListForHousing>>() {
            @Override
            protected ObservableList<WaitingListForHousing> call() throws Exception {
                List<WaitingListForHousing> list = WLFHRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
