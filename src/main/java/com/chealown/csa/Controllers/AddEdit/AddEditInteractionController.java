package com.chealown.csa.Controllers.AddEdit;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Interaction;
import com.chealown.csa.DataBase.Repositories.InteractionRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEditInteractionController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField childTF;

    @FXML
    private Button chooseChildrenBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField interactionDateTF;

    @FXML
    private ComboBox<String> interactionTypeCB;

    @FXML
    private ComboBox<String> organizationCB;

    @FXML
    private Label pageName;

    @FXML
    private TextField resultTF;

    Interaction interaction = StaticObjects.getInteraction();

    @FXML
    private void initialize() throws SQLException {
        fillComboBoxes();
        MaskUtil.applyDateMask(interactionDateTF);

        btnSave.setOnAction(actionEvent -> {
            try {
                saveChanges();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        exitBtn.setOnAction(actionEvent -> {
            try {
                ManageUtil.switchPage("Главная", "MainPage-view");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            StaticObjects.setInteraction(null);
        });

        pageName.setText("Добавление");

        if (interaction != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void fillComboBoxes() throws SQLException {
        ResultSet orgRs = DBConnector.query("""
                SELECT organization_name FROM organization""");
        while (orgRs.next())
            organizationCB.getItems().add(orgRs.getString("organization_name"));

        ResultSet intRs = DBConnector.query("""
                SELECT interaction_name FROM interaction_type""");
        while (intRs.next())
            interactionTypeCB.getItems().add(intRs.getString("interaction_name"));
    }

    private void loadData() {
        childTF.setText(String.valueOf(interaction.getIdChildren()));
        interactionDateTF.setText(interaction.getInteractionDate());
        interactionTypeCB.getSelectionModel().select(interaction.getInteractionType());
        organizationCB.getSelectionModel().select(interaction.getOrganization());
        resultTF.setText(interaction.getInteractionResult());
    }

    private void saveChanges() throws SQLException {
        if (
                childTF.getText().isEmpty() ||
                        interactionTypeCB.getSelectionModel().getSelectedItem() == null ||
                        organizationCB.getSelectionModel().getSelectedItem() == null
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (interaction == null)
                interaction = new Interaction();
            interaction.setIdChildren(Integer.parseInt(childTF.getText()));
            interaction.setInteractionResult(resultTF.getText());
            interaction.setOrganization(getData("organization", "organization_name", organizationCB.getSelectionModel().getSelectedItem(), "id"));
            interaction.setInteractionType(getData("interaction_type", "interaction_name", interactionTypeCB.getSelectionModel().getSelectedItem(), "id"));
            interaction.setIdUser(StaticObjects.getCurrentUser().getId());
            interaction.setInteractionDate(interactionDateTF.getText());


            String messagePart = "Добавление записи";
            if (StaticObjects.getInteraction() != null)
                messagePart = "Изменение записи";

            if (InteractionRepository.save(interaction)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                StaticObjects.setInteraction(null);
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
