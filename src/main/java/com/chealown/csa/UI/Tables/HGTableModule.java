package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.FamilySituation;
import com.chealown.csa.DataBase.Models.HealthGroup;
import com.chealown.csa.DataBase.Repositories.FamilySituationRepository;
import com.chealown.csa.DataBase.Repositories.HealthGroupRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class HGTableModule extends TableModule<HealthGroup> {

    public HGTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<HealthGroup> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<HealthGroup, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<HealthGroup, String> nameCol = new TableColumn<>("Название группы здоровья");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<HealthGroup>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<HealthGroup> call() throws Exception {
                List<HealthGroup> list = HealthGroupRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
