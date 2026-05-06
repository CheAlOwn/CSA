package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.DataBase.Repositories.SocialMonitoringRepository;
import com.chealown.csa.DataBase.Repositories.SocialPassportRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class SPTableModule extends TableModule<SocialPassport> {

    public SPTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<SocialPassport> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {

        TableColumn<SocialPassport, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<SocialPassport, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<SocialPassport, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<SocialPassport, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<SocialPassport, String> idChildrenCol = new TableColumn<>("ID ребенка");
        idChildrenCol.setCellValueFactory(new PropertyValueFactory<>("idChildren"));

        TableColumn<SocialPassport, String> educationCol = new TableColumn<>("Образование");
        educationCol.setCellValueFactory(new PropertyValueFactory<>("education"));

        TableColumn<SocialPassport, String> healthGroupCol = new TableColumn<>("Группа здоровья");
        healthGroupCol.setCellValueFactory(new PropertyValueFactory<>("healthGroup"));

        TableColumn<SocialPassport, String> familySituationCol = new TableColumn<>("Семейное положение");
        familySituationCol.setCellValueFactory(new PropertyValueFactory<>("familySituation"));

        TableColumn<SocialPassport, String> havingADisabilityCol = new TableColumn<>("Инвалидность");
        havingADisabilityCol.setCellValueFactory(new PropertyValueFactory<>("havingADisability"));

        TableColumn<SocialPassport, String> dateCreateCol = new TableColumn<>("Дата создания");
        dateCreateCol.setCellValueFactory(new PropertyValueFactory<>("dateCreate"));

        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                idChildrenCol,
                educationCol,
                healthGroupCol,
                familySituationCol,
                havingADisabilityCol,
                dateCreateCol
        );
    }

    @Override
    protected Task<ObservableList<SocialPassport>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<SocialPassport> call() throws Exception {
                List<SocialPassport> list = SocialPassportRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
