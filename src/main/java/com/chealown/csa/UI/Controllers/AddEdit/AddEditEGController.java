package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.EducationGroup;
import com.chealown.csa.DataBase.Models.Employee;
import com.chealown.csa.DataBase.Repositories.EducationGroupRepository;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Tables.EmployeeTableModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class AddEditEGController {

    public HBox tablePane;
    public VBox tableBox;
    @FXML
    private Button btnSave;

    @FXML
    private Button chooseTutorBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TextField groupNameTF;

    @FXML
    private Label pageName;

    @FXML
    private TextField tutorIdTF;

    private EducationGroup educationGroup = (EducationGroup) StaticObjects.getSelectedObject();

    boolean flag = false;

    @FXML
    private void initialize() {
        MaskUtil.applyNumberMask(tutorIdTF, 6);
        showTableBox(false);
        btnSave.setOnAction(actionEvent -> {
            try {
                saveChanges();
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

        if (educationGroup != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        groupNameTF.setText(educationGroup.getGroupName());
        tutorIdTF.setText(String.valueOf(educationGroup.getTutorId()));
    }

    private void saveChanges() throws IOException {
        if (groupNameTF.getText().isEmpty()) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (educationGroup == null) {
                educationGroup = new EducationGroup();
            }

            educationGroup.setGroupName(groupNameTF.getText());
            educationGroup.setTutorId(Integer.parseInt(tutorIdTF.getText()));

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (EducationGroupRepository.save(educationGroup)) {
                ManageUtil.showAlert(Alert.AlertType.INFORMATION, pageName.getText(), messagePart + " прошло успешно");
                ManageUtil.switchPage("Главная", "MainPage-view");
            } else
                ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), messagePart + " не удалось");
        }
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
            EmployeeTableModule module = new EmployeeTableModule();
            tablePane.getChildren().add(module.getTableView());

            module.getTableView().getSelectionModel().selectedItemProperty().addListener(lst -> {
                tutorIdTF.setText(String.valueOf(module.getTableView().getSelectionModel().getSelectedItem().getId()));
            });

            flag = true;
        } else {
            showTableBox(false);
            flag = false;
        }
    }

}
