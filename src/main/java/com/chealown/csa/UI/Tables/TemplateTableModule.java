package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Status;
import com.chealown.csa.DataBase.Models.TemplateDocument;
import com.chealown.csa.DataBase.Repositories.StatusRepository;
import com.chealown.csa.DataBase.Repositories.TemplateRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class TemplateTableModule extends TableModule<TemplateDocument> {

    public TemplateTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<TemplateDocument> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<TemplateDocument, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<TemplateDocument, String> nameCol = new TableColumn<>("Название шаблона");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TemplateDocument, String> createdCol = new TableColumn<>("Дата создания");
        createdCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<TemplateDocument, String> updatedCol = new TableColumn<>("Дата изменения");
        updatedCol.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        table.getColumns().addAll(
                idCol,
                nameCol,
                createdCol,
                updatedCol
        );
    }

    @Override
    protected Task<ObservableList<TemplateDocument>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<TemplateDocument> call() throws Exception {
                List<TemplateDocument> list = TemplateRepository.getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
