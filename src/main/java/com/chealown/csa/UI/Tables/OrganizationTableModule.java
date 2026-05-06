package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.DataBase.Models.InteractionType;
import com.chealown.csa.DataBase.Models.Organization;
import com.chealown.csa.DataBase.Repositories.EmployeeRepository;
import com.chealown.csa.DataBase.Repositories.OrganizationRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class OrganizationTableModule extends TableModule<Organization> {

    public OrganizationTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<Organization> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {
        TableColumn<Organization, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Organization, String> nameCol = new TableColumn<>("Название организации");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("organizationName"));

        TableColumn<Organization, String> cityCol = new TableColumn<>("Город");
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Organization, String> streetCol = new TableColumn<>("Улица");
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Organization, String> buildCol = new TableColumn<>("Здание");
        buildCol.setCellValueFactory(new PropertyValueFactory<>("build"));

        TableColumn<Organization, String> emailCol = new TableColumn<>("Электронная почта");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Organization, String> phoneCol = new TableColumn<>("Номер телефона");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Organization, String> typeCol = new TableColumn<>("Тип организации");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.getColumns().addAll(
                idCol,
                nameCol,
                cityCol,
                streetCol,
                buildCol,
                emailCol,
                phoneCol,
                typeCol
        );
    }

    @Override
    protected Task<ObservableList<Organization>> createLoadTask() {
        return new Task<ObservableList<Organization>>() {
            @Override
            protected ObservableList<Organization> call() throws Exception {
                List<Organization> list = OrganizationRepository
                        .getAllData();
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
