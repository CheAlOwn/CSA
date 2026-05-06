package com.chealown.csa.UI.Controllers.AddEdit;

import com.chealown.csa.DataBase.Models.WaitingListForHousing;
import com.chealown.csa.DataBase.Repositories.WLFHRepository;
import com.chealown.csa.Entities.MaskUtil;
import com.chealown.csa.Entities.ManageUtil;
import com.chealown.csa.Entities.StaticObjects;
import com.chealown.csa.UI.Tables.ChildrenTableModule;
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

public class AddEditWLFHController {

    public VBox tableBox;
    public HBox tablePane;
    @FXML
    private TextField childTF;

    @FXML
    private Button exitBtn;

    @FXML
    private Button btnSave;

    @FXML
    private TextField currentStepTF;

    @FXML
    private TextField dateAddedTF;

    @FXML
    private TextField expectedDateIssueTF;

    @FXML
    private TextField numberInQueueTF;

    @FXML
    private Label pageName;


    WaitingListForHousing wlfh = (WaitingListForHousing) StaticObjects.getSelectedObject();
    boolean flag = false;

    @FXML
    private void initialize() {
        MaskUtil.applyNumberMask(childTF, 6);
        MaskUtil.applyDateMask(dateAddedTF);
        MaskUtil.applyNumberMask(numberInQueueTF, 4);

        showTableBox(false);

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

        if (wlfh != null) {
            loadData();
            pageName.setText("Редактирование");
        }
    }

    private void loadData() {
        currentStepTF.setText(String.valueOf(wlfh.getCurrentStep()));
        dateAddedTF.setText(wlfh.getDateAdded());
        expectedDateIssueTF.setText(wlfh.getExpectedDateOfIssue());
        numberInQueueTF.setText(String.valueOf(wlfh.getNumberInTheQueue()));
        childTF.setText(String.valueOf(wlfh.getIdChildren()));
    }

    private void saveChanges() throws SQLException, IOException {
        if (
                childTF.getText().isEmpty() ||
                        currentStepTF.getText().isEmpty() ||
                        expectedDateIssueTF.getText().isEmpty() ||
                        numberInQueueTF.getText().isEmpty() ||
                        dateAddedTF.getText().isEmpty()
        ) {
            ManageUtil.showAlert(Alert.AlertType.WARNING, pageName.getText(), "Не все обязательные поля заполнены");
        } else {
            if (wlfh == null)
                wlfh = new WaitingListForHousing();
            wlfh.setIdChildren(Integer.parseInt(childTF.getText()));
            wlfh.setCurrentStep(currentStepTF.getText());
            wlfh.setDateAdded(dateAddedTF.getText());
            wlfh.setExpectedDateOfIssue(expectedDateIssueTF.getText());
            wlfh.setNumberInTheQueue(Integer.parseInt(numberInQueueTF.getText()));

            String messagePart = "Добавление записи";
            if (StaticObjects.getSelectedObject() != null)
                messagePart = "Изменение записи";

            if (WLFHRepository.save(wlfh)) {
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
