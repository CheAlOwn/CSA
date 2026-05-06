package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Post;
import com.chealown.csa.DataBase.Models.Status;
import com.chealown.csa.DataBase.Repositories.PostRepository;
import com.chealown.csa.DataBase.Repositories.StatusRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class StatusTableModule extends TableModule<Status> {

    public StatusTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Status> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Status, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Status, String> nameCol = new TableColumn<>("Название статуса");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("statusName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<Status>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<Status> call() throws Exception {
                List<Status> list = StatusRepository.getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
