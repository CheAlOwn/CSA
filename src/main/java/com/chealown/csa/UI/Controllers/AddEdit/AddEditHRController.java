package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.HousingRights;
import com.chealown.csa.DataBase.Repositories.HousingRightsRepository;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Tables.ChildrenTableModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEditHRController {

    public VBox tableBox;
    public HBox tablePane;
    @FXML
    private Button chooseChildrenBtn;

    @FXML
    private TextField childTF;

    @FXML
    private Button exitBtn;

    @FXML
    private ComboBox<String> availabilityHousingCB;

    @FXML
    private Button btnSave;

    @FXML
    private TextField buildTF;

    @FXML
    private TextField cityTF;

    @FXML
    private ComboBox<String> formOwnershipCB;

    @FXML
    private Label pageName;

    @FXML
    private TextField registrationDateTF;

    @FXML
    private TextField streetTF;

    HousingRights housingRights = (HousingRights) StaticObjects.getSelectedObject();
    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    boolean flag = false;

    @FXML
    private void initialize() throws SQLException {
        MaskUtil.applyDateMask(registrationDateTF);
        MaskUtil.applyNumberMask(childTF, 6);

        showTableBox(false);

        fillComboBoxes();

        btnSave.setOnAction(actionEvent -> {
            try {
                saveChanges();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pageName.setText("Добавление");

        if (housingRights != null) {
            loadData();
            pageName.setText("Редактирование");
        }

    }

    private void fillComboBoxes() throws SQLException {
        ResultSet rs = DBConnector.query("""
                SELECT form_name FROM ownership_form""");
        while (rs.next())
            formOwnershipCB.getItems().add(rs.getString("form_name"));

        availabilityHousingCB.getItems().addAll("Да", "Нет");
    }

    private void loadData() {
        availabilityHousingCB.getSelectionModel().select(housingRights.getAvailabilityOfHousing());
        childTF.setText(String.valueOf(housingRights.getIdChildren()));
        buildTF.setText(housingRights.getBuild());
        streetTF.setText(housingRights.getStreet());
        cityTF.setText(housingRights.getCity());
        formOwnershipCB.getSelectionModel().select(housingRights.getFormOfOwnership());
        registrationDateTF.setText(housingRights.getRegistrationDate());
    }

    private void saveChanges() throws SQLException, IOException {

        if (
                availabilityHousingCB.getSelectionModel().getSelectedItem() == null ||
                        formOwnershipCB.getSelectionModel().getSelectedItem() == null ||
                        childTF.getText().isEmpty()
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (housingRights == null)
                housingRights = new HousingRights();
            housingRights.setIdChildren(Integer.parseInt(childTF.getText()));
            housingRights.setAvailabilityOfHousing(availabilityHousingCB.getSelectionModel().getSelectedItem());
            housingRights.setFormOfOwnership(getData("ownership_form", "form_name", formOwnershipCB.getSelectionModel().getSelectedItem(), "id"));
            housingRights.setBuild(buildTF.getText());
            housingRights.setStreet(streetTF.getText());
            housingRights.setCity(cityTF.getText());
            housingRights.setRegistrationDate(registrationDateTF.getText());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (HousingRightsRepository.save(housingRights)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");

            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");

        }
    }

    private String getData(String tableName, String col, String findData, String getData) throws SQLException {
        String sql = "SELECT * FROM " + tableName;

        ResultSet rs = DBConnector.query(sql);

        while (rs.next()) {
            if (rs.getString(col).equals(findData))
                return rs.getString(getData);

        }
        return null;
    }

    private void showTableBox(boolean val) {
        tableBox.setManaged(val);
        tableBox.setVisible(val);
    }

    @FXML
    private void showTable(ActionEvent actionEvent) throws SQLException {
        tablePane.getChildren().clear();

        if (!flag) {
            showTableBox(true);
            ChildrenTableModule module = new ChildrenTableModule();
            tablePane.getChildren().add(module.getTableView());

            module.getTableView().getSelectionModel().selectedItemProperty().addListener(lst -> {
                childTF.setText(String.valueOf(module.getTableView().getSelectionModel().getSelectedItem().getId()));
            });

            flag = true;
        } else {
            showTableBox(false);
            flag = false;
        }
    }


}
