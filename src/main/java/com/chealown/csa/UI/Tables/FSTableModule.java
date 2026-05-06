package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Models.FamilySituation;
import com.chealown.csa.DataBase.Repositories.EmployeeRepository;
import com.chealown.csa.DataBase.Repositories.FamilySituationRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class FSTableModule extends TableModule<FamilySituation> {

    public FSTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<FamilySituation> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<FamilySituation, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<FamilySituation, String> nameCol = new TableColumn<>("Название семейного положения");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("situationName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<FamilySituation>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<FamilySituation> call() throws Exception {
                List<FamilySituation> list = FamilySituationRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
