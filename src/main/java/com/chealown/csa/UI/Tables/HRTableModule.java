package com.chealown.csa.UI.Tables;

import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Models.HealthGroup;
import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.DataBase.Repositories.HealthGroupRepository;
import com.chealown.csa.DataBase.Repositories.HousingRightsRepository;
import com.chealown.csa.Entities.StaticObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class HRTableModule extends TableModule<HousingRights> {

    public HRTableModule() throws SQLException {
    }

    @Override
    protected ObservableList<HousingRights> createDataList() {
        return FXCollections.observableArrayList();
    }

    @Override
    protected void setupColumns() {

        TableColumn<HousingRights, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<HousingRights, String> secondNameCol = new TableColumn<>("Фамилия");
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));

        TableColumn<HousingRights, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<HousingRights, String> patronymicCol = new TableColumn<>("Отчество");
        patronymicCol.setCellValueFactory(new PropertyValueFactory<>("patronymic"));

        TableColumn<HousingRights, String> childIdCol = new TableColumn<>("ID ребенка");
        childIdCol.setCellValueFactory(new PropertyValueFactory<>("idChildren"));

        TableColumn<HousingRights, String> availabilityOfHousingCol = new TableColumn<>("Наличие жилья");
        availabilityOfHousingCol.setCellValueFactory(new PropertyValueFactory<>("availabilityOfHousing"));

        TableColumn<HousingRights, String> formOfOwnershipCol = new TableColumn<>("Форма собственности");
        formOfOwnershipCol.setCellValueFactory(new PropertyValueFactory<>("formOfOwnership"));

        TableColumn<HousingRights, String> registrationDateCol = new TableColumn<>("Дата регистрации");
        registrationDateCol.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        TableColumn<HousingRights, String> cityCol = new TableColumn<>("Город");
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<HousingRights, String> streetCol = new TableColumn<>("Улица");
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<HousingRights, String> buildCol = new TableColumn<>("Здание");
        buildCol.setCellValueFactory(new PropertyValueFactory<>("build"));



        table.getColumns().addAll(
                idCol,
                secondNameCol,
                firstNameCol,
                patronymicCol,
                childIdCol,
                availabilityOfHousingCol,
                formOfOwnershipCol,
                registrationDateCol,
                cityCol,
                streetCol,
                buildCol
        );
    }

    @Override
    protected Task<ObservableList<HousingRights>> createLoadTask() {
        return new Task<>() {
            @Override
            protected ObservableList<HousingRights> call() throws Exception {
                List<HousingRights> list = HousingRightsRepository
                        .getAllData(StaticObjects.getCurrentUser().getPost().equals("Администратор ПО"));
                return FXCollections.observableArrayList(list);
            }
        };
    }
}
