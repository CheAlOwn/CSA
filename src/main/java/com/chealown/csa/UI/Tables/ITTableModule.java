package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.HealthGroup;
import com.chealown.csa.DataBase.Models.InteractionType;
import com.chealown.csa.DataBase.Repositories.HealthGroupRepository;
import com.chealown.csa.DataBase.Repositories.InteractionTypeRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ITTableModule extends TableModule<InteractionType> {

    public ITTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<InteractionType> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<InteractionType, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<InteractionType, String> nameCol = new TableColumn<>("Название типа взаимодействия");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("interactionName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<InteractionType>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<InteractionType> call() throws Exception {
                List<InteractionType> list = InteractionTypeRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
