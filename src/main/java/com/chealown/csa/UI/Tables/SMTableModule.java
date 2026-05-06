package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Interaction;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.DataBase.Repositories.InteractionRepository;
import com.chealown.csa.DataBase.Repositories.SocialMonitoringRepository;
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

public class SMTableModule extends TableModule<SocialMonitoring> {

    public SMTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<SocialMonitoring> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<SocialMonitoring, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<SocialMonitoring, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<SocialMonitoring, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<SocialMonitoring, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<SocialMonitoring, String> idChildrenCol = new TableColumn<>("ID ребенка");
        idChildrenCol.setCellValueFactory(new PropertyValueFactory<>("idChildren"));

        TableColumn<SocialMonitoring, String> fixationDateCol = new TableColumn<>("Дата фиксации");
        fixationDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfFixation"));

        TableColumn<SocialMonitoring, String> monitoringTypeCol = new TableColumn<>("Тип мониторинга");
        monitoringTypeCol.setCellValueFactory(new PropertyValueFactory<>("monitoringType"));

        TableColumn<SocialMonitoring, String> oldValCol = new TableColumn<>("Старое значение");
        oldValCol.setCellValueFactory(new PropertyValueFactory<>("oldValue"));

        TableColumn<SocialMonitoring, String> newValCol = new TableColumn<>("Новое значение");
        newValCol.setCellValueFactory(new PropertyValueFactory<>("newValue"));

        TableColumn<SocialMonitoring, String> reasonCol = new TableColumn<>("Причина изменения");
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("changeReason"));

        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                idChildrenCol,
                fixationDateCol,
                monitoringTypeCol,
                oldValCol,
                newValCol,
                reasonCol
        );
    }

    @Override
    protected Task<ObservableList<SocialMonitoring>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<SocialMonitoring> call() throws Exception {
                List<SocialMonitoring> list = SocialMonitoringRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
