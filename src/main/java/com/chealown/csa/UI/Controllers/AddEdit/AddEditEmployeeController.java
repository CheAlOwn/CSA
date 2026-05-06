package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.DBConnector;
import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Repositories.EmployeeRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEditEmployeeController {

    @FXML
    private Button btnSave;

    @FXML
    private TextField emailTF;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField firstNameTF;

    @FXML
    private Label pageName;

    @FXML
    private TextField patronymicTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private ComboBox<String> postCB;

    @FXML
    private TextField secondNameTF;

    private Employee employee = (Employee) StaticObjects.getSelectedObject();

    @FXML
    private void initialize() throws SQLException {

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

        if (employee != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void fillComboBoxes() throws SQLException {

        ResultSet rs = DBConnector.query("""
                SELECT post_name FROM post""");
        while (rs.next())
            postCB.getItems().add(rs.getString("post_name"));
    }

    private void loadData() {
        secondNameTF.setText(employee.getSecondName());
        firstNameTF.setText(employee.getFirstName());
        patronymicTF.setText(employee.getPatronymic());
        emailTF.setText(employee.getEmail());
        postCB.getSelectionModel().select(employee.getPost());
        phoneTF.setText(employee.getPhone());

    }

    private void saveChanges() throws SQLException, IOException {
        if (
                secondNameTF.getText().isEmpty() ||
                        firstNameTF.getText().isEmpty() ||
                        postCB.getSelectionModel().getSelectedItem() == null ||
                        postCB.getSelectionModel().getSelectedItem().isEmpty()
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (employee == null)
                employee = new Employee();
            employee.setSecondName(secondNameTF.getText());
            employee.setFirstName(firstNameTF.getText());
            employee.setPatronymic(patronymicTF.getText());
            employee.setPhone(phoneTF.getText());
            employee.setPost(getData("post", "post_name", postCB.getSelectionModel().getSelectedItem(), "id"));

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (EmployeeRepository.save(employee)) {
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
