package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.SocialMonitoring;
import com.chealown.csa.DataBase.Repositories.SocialMonitoringRepository;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddEditSMController {

    @FXML
    private TextField childTF;

    @FXML
    private Button exitBtn;

    @FXML
    private Button btnSave;

    @FXML
    private TextField changeReason;

    @FXML
    private ComboBox<String> monitoringTypeCB;

    @FXML
    private TextField newValueTF;

    @FXML
    private TextField oldValueTF;

    @FXML
    private Label pageName;

    SocialMonitoring socialMonitoring = (SocialMonitoring) StaticObjects.getSelectedObject();

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

        if (socialMonitoring != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void fillComboBoxes() throws SQLException {
        ResultSet rs = DBConnector.query("""
                SELECT monitoring_name FROM monitoring_type""");
        while (rs.next())
            monitoringTypeCB.getItems().add(rs.getString("monitoring_name"));
    }

    private void loadData() {
        changeReason.setText(socialMonitoring.getChangeReason());
        monitoringTypeCB.getSelectionModel().select(socialMonitoring.getMonitoringType());
        oldValueTF.setText(socialMonitoring.getOldValue());
        newValueTF.setText(socialMonitoring.getNewValue());
        childTF.setText(String.valueOf(socialMonitoring.getIdChildren()));
    }

    private void saveChanges() throws SQLException, IOException {
        if (
                childTF.getText().isEmpty() ||
                        monitoringTypeCB.getSelectionModel().getSelectedItem() == null ||
                        monitoringTypeCB.getSelectionModel().getSelectedItem().equals("Отсутствует") ||
                        oldValueTF.getText().isEmpty() ||
                        newValueTF.getText().isEmpty() ||
                        changeReason.getText().isEmpty()
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (socialMonitoring == null)
                socialMonitoring = new SocialMonitoring();
            socialMonitoring.setIdChildren(Integer.parseInt(childTF.getText()));
            socialMonitoring.setChangeReason(changeReason.getText());
            socialMonitoring.setDateOfFixation(MaskUtil.formattedDate(String.valueOf(LocalDate.now())));
            socialMonitoring.setOldValue(oldValueTF.getText());
            socialMonitoring.setNewValue(newValueTF.getText());
            socialMonitoring.setMonitoringType(getData("monitoring_type", "monitoring_name", monitoringTypeCB.getSelectionModel().getSelectedItem(), "id"));
            socialMonitoring.setIdUser(StaticObjects.getCurrentUser().getId());


            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (SocialMonitoringRepository.save(socialMonitoring)) {
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
