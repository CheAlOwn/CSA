package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Models.EducationGroup;
import com.chealown.csa.DataBase.Repositories.DocumentRepository;
import com.chealown.csa.DataBase.Repositories.EducationGroupRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class EGTableModule extends TableModule<EducationGroup> {

    public EGTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<EducationGroup> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<EducationGroup, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<EducationGroup, String> nameCol = new TableColumn<>("Название группы");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        TableColumn<EducationGroup, String> tutorIdCol = new TableColumn<>("ID куратора");
        tutorIdCol.setCellValueFactory(new PropertyValueFactory<>("tutorId"));

        TableColumn<EducationGroup, String> secondNameCol = new TableColumn<>("Фамилия куратора");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<EducationGroup, String> firstNameCol = new TableColumn<>("Имя куратора");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<EducationGroup, String> patronymicCol = new TableColumn<>("Отчество куратора");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        table.getColumns().addAll(
                idCol,
                nameCol,
                tutorIdCol,
                secondNameCol,
                firstNameCol,
                patronymicCol
        );
    }

    @Override
    protected Task<ObservableList<EducationGroup>> createLoadTask() {
        return new Task<ObservableList<EducationGroup>>() {
            @Override
            protected ObservableList<EducationGroup> call() throws Exception {
                List<EducationGroup> list = EducationGroupRepository.getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
