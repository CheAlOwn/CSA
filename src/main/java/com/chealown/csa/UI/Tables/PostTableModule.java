package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.OrganizationType;
import com.chealown.csa.DataBase.Models.Post;
import com.chealown.csa.DataBase.Repositories.OrganizationTypeRepository;
import com.chealown.csa.DataBase.Repositories.PostRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class PostTableModule extends TableModule<Post> {

    public PostTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Post> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Post, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Post, String> nameCol = new TableColumn<>("Название должности");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("postName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<Post>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<Post> call() throws Exception {
                List<Post> list = PostRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
