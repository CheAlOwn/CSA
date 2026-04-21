package com.chealown.csa.Controllers;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Children;
import com.chealown.csa.DataBase.Repositories.ChildrenRepository;
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
import java.time.format.DateTimeFormatter;

public class AddEditChildrenController {

    @FXML
    private Button exitBtn;

    @FXML
    private TextField birthdateTF;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> educationGroupCB;

    @FXML
    private TextField firstNameTF;

    @FXML
    private ComboBox<String> genderCB;

    @FXML
    private Label pageName;

    @FXML
    private TextField passportNumTF;

    @FXML
    private TextField passportSerTF;

    @FXML
    private TextField patronymicTF;

    @FXML
    private TextField registrationDateTF;

    @FXML
    private TextField secondNameTF;

    @FXML
    private TextField snilsTF;

    @FXML
    private ComboBox<String> statusCB;

    SecretKey key = SecurityUtil.loadKeyFromEnv("APP_ENCRYPTION_KEY");
    Children child = StaticObjects.getChildren();

    @FXML
    private void initialize() throws SQLException {

        fillComboBoxes();

        MaskUtil.applyDateMask(birthdateTF);
        MaskUtil.applyDateMask(registrationDateTF);

        MaskUtil.applyNumberMask(passportSerTF, 4);
        MaskUtil.applyNumberMask(passportNumTF, 6);

        MaskUtil.applySnilsMask(snilsTF);

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

            StaticObjects.setChildren(null);
        });

        pageName.setText("Добавление");

        if (child != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void fillComboBoxes() throws SQLException {

        ResultSet eduRs = DBConnector.query("""
                SELECT group_name FROM education_group""");
        while (eduRs.next())
            educationGroupCB.getItems().add(SecurityUtil.decryptSafe(eduRs.getString("group_name"), key));

        ResultSet genRs = DBConnector.query("""
                SELECT gender_name FROM gender""");
        while (genRs.next())
            genderCB.getItems().add(SecurityUtil.decryptSafe(genRs.getString("gender_name"), key));

        ResultSet statRs = DBConnector.query("""
                SELECT status_name FROM status""");
        while (statRs.next())
            statusCB.getItems().add(SecurityUtil.decryptSafe(statRs.getString("status_name"), key));
    }

    private void loadData() {
        secondNameTF.setText(child.getSecondName());
        firstNameTF.setText(child.getFirstName());
        patronymicTF.setText(child.getPatronymic());
        birthdateTF.setText(child.getBirthdate());
        educationGroupCB.getSelectionModel().select(child.getEducationGroup());
        genderCB.getSelectionModel().select(child.getGender());
        passportNumTF.setText(child.getPassportNum());
        passportSerTF.setText(child.getPassportSer());
        registrationDateTF.setText(child.getRegistrationDate());
        snilsTF.setText(child.getSnils());
        statusCB.getSelectionModel().select(child.getStatus());

    }

    private void saveChanges() throws SQLException {
        if (
                secondNameTF.getText().isEmpty() ||
                        firstNameTF.getText().isEmpty() ||
                        birthdateTF.getText().isEmpty() ||
                        genderCB.getSelectionModel().getSelectedItem() == null ||
                        genderCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        passportSerTF.getText().isEmpty() ||
                        passportNumTF.getText().isEmpty() ||
                        registrationDateTF.getText().isEmpty() ||
                        snilsTF.getText().isEmpty() ||
                        statusCB.getSelectionModel().getSelectedItem() == null ||
                        statusCB.getSelectionModel().getSelectedItem().equals("Отсутствует")
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (child == null)
                child = new Children();
            child.setSecondName(secondNameTF.getText());
            child.setFirstName(firstNameTF.getText());
            child.setPatronymic(patronymicTF.getText());

            // TODO: добавить маску
            child.setBirthdate(birthdateTF.getText());
            child.setRegistrationDate(registrationDateTF.getText());
            child.setEducationGroup(getData("education_group", "group_name", educationGroupCB.getSelectionModel().getSelectedItem(), "id"));
            System.out.println(getData("education_group", "group_name", educationGroupCB.getSelectionModel().getSelectedItem(), "id"));
            child.setGender(getData("gender", "gender_name", genderCB.getSelectionModel().getSelectedItem(), "id"));
            child.setStatus(getData("status", "status_name", statusCB.getSelectionModel().getSelectedItem(), "id"));
            child.setPassportNum(passportNumTF.getText());
            child.setPassportSer(passportSerTF.getText());
            child.setSnils(snilsTF.getText());


            String messagePart = "Добавление записи";
            if (StaticObjects.getChildren() != null)
                messagePart = "Изменение записи";

            if (ChildrenRepository.save(child)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                StaticObjects.setChildren(null);
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
