package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.DataBase.Models.Interaction;
import com.chealown.csa.DataBase.Repositories.HousingRightsRepository;
import com.chealown.csa.DataBase.Repositories.InteractionRepository;
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

public class InteractionTableModule extends TableModule<Interaction> {

    public InteractionTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Interaction> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Interaction, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Interaction, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<Interaction, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Interaction, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<Interaction, String> idChildrenCol = new TableColumn<>("ID ребенка");
        idChildrenCol.setCellValueFactory(new PropertyValueFactory<>("idChildren"));

        TableColumn<Interaction, String> interactionDateCol = new TableColumn<>("Дата взаимодействия");
        interactionDateCol.setCellValueFactory(new PropertyValueFactory<>("interactionDate"));

        TableColumn<Interaction, String> organizationCol = new TableColumn<>("Название организации");
        organizationCol.setCellValueFactory(new PropertyValueFactory<>("organization"));

        TableColumn<Interaction, String> interactionTypeCol = new TableColumn<>("Тип взаимодействия");
        interactionTypeCol.setCellValueFactory(new PropertyValueFactory<>("interactionType"));

        TableColumn<Interaction, String> interactionResultCol = new TableColumn<>("Результат");
        interactionResultCol.setCellValueFactory(new PropertyValueFactory<>("interactionResult"));

        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                idChildrenCol,
                interactionDateCol,
                organizationCol,
                interactionTypeCol,
                interactionResultCol
        );
    }

    @Override
    protected Task<ObservableList<Interaction>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<Interaction> call() throws Exception {
                List<Interaction> list = InteractionRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
