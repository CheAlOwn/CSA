package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.InteractionType;
import com.chealown.csa.DataBase.Models.OrganizationType;
import com.chealown.csa.DataBase.Repositories.InteractionTypeRepository;
import com.chealown.csa.DataBase.Repositories.OrganizationTypeRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class OTTableModule extends TableModule<OrganizationType> {

    public OTTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<OrganizationType> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<OrganizationType, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrganizationType, String> nameCol = new TableColumn<>("Название типа организации");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("typeName"));

        table.getColumns().addAll(
                idCol,
                nameCol
        );
    }

    @Override
    protected Task<ObservableList<OrganizationType>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<OrganizationType> call() throws Exception {
                List<OrganizationType> list = OrganizationTypeRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
