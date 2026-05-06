package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Models.Document;
import com.chealown.csa.DataBase.Repositories.ChildrenRepository;
import com.chealown.csa.DataBase.Repositories.DocumentRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class DocumentTableModule extends TableModule<Document> {

    public DocumentTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Document> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Document, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Document, String> nameCol = new TableColumn<>("Название документа");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("label"));

        TableColumn<Document, String> templateCol = new TableColumn<>("Шаблон");
        templateCol.setCellValueFactory(new PropertyValueFactory<>("template"));

        TableColumn<Document, String> createDateCol = new TableColumn<>("Дата создания");
        createDateCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));


        table.getColumns().addAll(
                idCol,
                nameCol,
                templateCol,
                createDateCol
        );
    }

    @Override
    protected Task<ObservableList<Document>> createLoadTask() {
        return new Task<ObservableList<Document>>() {
            @Override
            protected ObservableList<Document> call() throws Exception {
                List<Document> list = DocumentRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
