package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Organization;
import com.chealown.csa.DataBase.Repositories.OrganizationRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEditOrganizationController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField buildTF;

    @FXML
    private TextField cityTF;

    @FXML
    private TextField emailTF;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField nameTF;

    @FXML
    private Label pageName;

    @FXML
    private TextField phoneTF;

    @FXML
    private TextField streetTF;

    @FXML
    private ComboBox<String> typeCB;

    private Organization organization = (Organization) StaticObjects.getSelectedObject();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();

        btnSave.setOnAction(actionEvent -> {
            try {
                saveChanges();
            } catch (SQLException | IOException e) {
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

        if (organization != null) {
            loadData();
            pageName.setText("Редактирование");
        }

    }

    private void fillComboBoxes() throws SQLException {
        ResultSet rs = DBConnector.query("""
                SELECT type_name FROM organization_type""");
        while (rs.next())
            typeCB.getItems().add(rs.getString("type_name"));
    }

    private void loadData() {
        nameTF.setText(organization.getOrganizationName());
        buildTF.setText(organization.getBuild());
        streetTF.setText(organization.getStreet());
        cityTF.setText(organization.getCity());
        emailTF.setText(organization.getEmail());
        phoneTF.setText(organization.getPhone());
        typeCB.getSelectionModel().select(organization.getType());
    }

    private void saveChanges() throws SQLException, IOException {

        if (
                nameTF.getText().isEmpty() ||
                        phoneTF.getText().isEmpty() ||
                        typeCB.getSelectionModel().getSelectedItem() == null
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (organization == null)
                organization = new Organization();
            organization.setBuild(buildTF.getText());
            organization.setStreet(streetTF.getText());
            organization.setCity(cityTF.getText());
            organization.setEmail(emailTF.getText());
            organization.setPhone(phoneTF.getText());
            organization.setOrganizationName(nameTF.getText());
            organization.setType(getData("organization_type", "type_name", typeCB.getSelectionModel().getSelectedItem(), "id"));

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (OrganizationRepository.save(organization)) {
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

}
