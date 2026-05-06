package com.chealown.csa.UI.Tables;

import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class TableModule<T> {
    protected TableView<T> table;
    protected ObservableList<T> data;

    public TableModule() throws SQLException {
        this.table = new TableView<>();
        setTableSize();
        this.data = createDataList();
        loadData();
        setupColumns();
        this.table.setItems(data);
    }

    protected abstract ObservableList<T> createDataList();

    protected abstract void setupColumns();

    protected abstract Task<ObservableList<T>> createLoadTask();

    public TableView<T> getTableView() {
        return table;
    }

    public void loadData() throws SQLException {
        Task<ObservableList<T>> loadTask = createLoadTask();
        loadTask.setOnSucceeded(event -> {
            data.setAll(loadTask.getValue());
            StaticObjects.setListTableData(List.copyOf(data));
            setMapData();
        });

        loadTask.setOnFailed(event -> {
            Throwable exception = loadTask.getException();
            System.err.println("Ошибка загрузки данных: " + exception.getMessage());
        });

        new Thread(loadTask).start();
    }

    public void loadData(List<T> newData) throws SQLException {
        Task<ObservableList<T>> loadTask = createLoadTask();
        loadTask.setOnSucceeded(event -> {
            data.setAll(FXCollections.observableArrayList(newData));
        });

        loadTask.setOnFailed(event -> {
            Throwable exception = loadTask.getException();
            System.err.println("Ошибка загрузки данных: " + exception.getMessage());
        });

        new Thread(loadTask).start();
    }

    private void setMapData() {
        List<Map<String, Object>> data = new ArrayList<>();

        for (int i = 0; i < table.getItems().size(); i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < table.getColumns().size(); j++) {
                map.put(((TableColumn) table.getColumns().get(j)).getText(),  ((TableColumn) table.getColumns().get(j)).getCellData(i));
            }
            data.add(map);
        }

        StaticObjects.setMapTableData(data);
    }


    private void setTableSize() {
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
    }

}
