package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.SocialPassport;
import com.chealown.csa.DataBase.Repositories.SocialPassportRepository;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.SecurityUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddEditSPController {

    @FXML
    private TextField childTF;

    @FXML
    private Button exitBtn;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> disabilityCB;

    @FXML
    private ComboBox<String> educationLevelCB;

    @FXML
    private ComboBox<String> familySituationCB;

    @FXML
    private ComboBox<String> healthGroupCB;

    @FXML
    private Label pageName;

    SocialPassport socialPassport = StaticObjects.getSocialPassport();
    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");

    @FXML
    private void initialize() throws SQLException {

        fillComboBoxes();

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

            StaticObjects.setSocialPassport(null);
        });

        pageName.setText("Добавление");

        if (socialPassport != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        disabilityCB.getSelectionModel().select(socialPassport.getHavingADisability());
        educationLevelCB.getSelectionModel().select(socialPassport.getEducation());
        familySituationCB.getSelectionModel().select(socialPassport.getFamilySituation());
        healthGroupCB.getSelectionModel().select(socialPassport.getHealthGroup());
        childTF.setText(String.valueOf(socialPassport.getIdChildren()));
    }

    private void fillComboBoxes() throws SQLException {


        ResultSet eduRs = DBConnector.query("""
                SELECT education_name FROM education_level""");
        while (eduRs.next())
            educationLevelCB.getItems().add(SecurityUtil.decryptSafe(eduRs.getString("education_name"), key));

        ResultSet famRs = DBConnector.query("""
                SELECT situation_name FROM family_situation""");
        while(famRs.next())
            familySituationCB.getItems().add(SecurityUtil.decryptSafe(famRs.getString("situation_name"), key));

        ResultSet heaRs = DBConnector.query("""
                SELECT group_name FROM health_group""");
        while (heaRs.next())
            healthGroupCB.getItems().add(SecurityUtil.decryptSafe(heaRs.getString("group_name"), key));

        disabilityCB.getItems().addAll("Да", "Нет");
    }

    private void saveChanges() throws SQLException {
        //
        // TODO: для комбо-боксов в таблицы добавить значение "Не указано"
        //

        if (
                childTF.getText().isEmpty() ||
                        disabilityCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        educationLevelCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        familySituationCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        healthGroupCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        disabilityCB.getSelectionModel().getSelectedItem() == null ||
                        educationLevelCB.getSelectionModel().getSelectedItem() == null ||
                        familySituationCB.getSelectionModel().getSelectedItem() == null ||
                        healthGroupCB.getSelectionModel().getSelectedItem() == null
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (socialPassport == null)
                socialPassport = new SocialPassport();
            socialPassport.setIdChildren(Integer.parseInt(childTF.getText()));
            socialPassport.setDateCreate(MaskUtil.formattedDate(String.valueOf(LocalDate.now())));
            socialPassport.setEducation(getData("education_level", "education_name", educationLevelCB.getSelectionModel().getSelectedItem(), "id"));
            socialPassport.setFamilySituation(getData("family_situation", "situation_name", familySituationCB.getSelectionModel().getSelectedItem(), "id"));
            socialPassport.setHealthGroup(getData("health_group", "group_name", healthGroupCB.getSelectionModel().getSelectedItem(), "id"));
            socialPassport.setHavingADisability(disabilityCB.getSelectionModel().getSelectedItem());

            String messagePart = "Добавление записи";
            if (StaticObjects.getSocialPassport() != null)
                messagePart = "Изменение записи";

            if (SocialPassportRepository.save(socialPassport)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                StaticObjects.setSocialPassport(null);
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");

        }
    }

    private String getData(String tableName, String col, String findData, String getData) throws SQLException {
        String sql = "SELECT * FROM " + tableName;

        ResultSet rs = DBConnector.query(sql);

        while (rs.next()) {
            if (SecurityUtil.decryptSafe(rs.getString(col), key).equals(findData))
                return rs.getString(getData);

        }
        return null;
    }
}
