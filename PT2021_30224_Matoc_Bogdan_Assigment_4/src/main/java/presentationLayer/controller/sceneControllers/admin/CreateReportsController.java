package presentationLayer.controller.sceneControllers.admin;

import businessLogic.order.OrderLogic;
import exceptions.FormatInputException;
import exceptions.IncorrectStartEndHourException;
import exceptions.NullInputExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentationLayer.controller.switchScene.AdminControlMenuHandle;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.switchScene.SwitchSceneInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateReportsController implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    private OrderLogic orderLogic;
    private SwitchSceneInterface switchScene;
    @FXML
    private Button homeButton;
    @FXML
    private Button StockButton;
    @FXML
    private Button updateMenuButton;
    @FXML
    private Button createMenuButton;
    @FXML
    private Button createReportsButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button getOrdersFromIntervalButton;
    @FXML
    private Button getProductTopButton;
    @FXML
    private Button getClientTopButton;
    @FXML
    private Button getDayReportButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Integer> startHourComboBox;
    @FXML
    private ComboBox<Integer> endHourComboBox;
    @FXML
    private TextField noMinTextField;
    @FXML
    private TextField valueMinTextField;
    @FXML
    private Label errorLabel;

    public CreateReportsController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
        orderLogic = new OrderLogic();
        switchScene = new SwitchSceneClass();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(homeButton, StockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        initHoursChoiceBox();
        errorLabel.setVisible(false);
        getOrdersFromIntervalButton.setOnAction(this::getOrdersFromIntervalButtonHandle);
        getProductTopButton.setOnAction(this::getProductTopButtonHandle);
        getDayReportButton.setOnAction(this::getDayReportButtonHandle);
        getClientTopButton.setOnAction(this::getClientTopButtonHandle);

    }

    private void getOrdersFromIntervalButtonHandle(ActionEvent actionEvent) {
        try {
            orderLogic.getOrdersFromInterval(datePicker.getValue(), startHourComboBox.getValue(), endHourComboBox.getValue());
            switchSceneToReportScene(actionEvent);
        } catch (NullInputExceptions | IncorrectStartEndHourException exceptions) {
            errorLabel.setText(exceptions.getMessage());
            errorLabel.setVisible(true);
        }
    }

    private void getProductTopButtonHandle(ActionEvent actionEvent) {
        try {
            orderLogic.getTopProduct(noMinTextField.getText());
            switchSceneToReportScene(actionEvent);
        } catch (NullInputExceptions | FormatInputException exceptions) {
            errorLabel.setText(exceptions.getMessage());
            errorLabel.setVisible(true);
        }
    }

    private void getDayReportButtonHandle(ActionEvent actionEvent) {
        try {
            orderLogic.getDayReport(datePicker.getValue(), noMinTextField.getText());
            switchSceneToReportScene(actionEvent);
        } catch (NullInputExceptions | FormatInputException exceptions) {
            errorLabel.setText(exceptions.getMessage());
            errorLabel.setVisible(true);
        }
    }

    private void getClientTopButtonHandle(ActionEvent actionEvent) {
        try {
            orderLogic.getClientTop(noMinTextField.getText(), valueMinTextField.getText());
            switchSceneToReportScene(actionEvent);
        } catch (NullInputExceptions | FormatInputException exceptions) {
            errorLabel.setText(exceptions.getMessage());
            errorLabel.setVisible(true);
        }
    }

    private void initHoursChoiceBox() {
        ObservableList<Integer> hours = FXCollections.observableList(new ArrayList<>(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)));
        startHourComboBox.setItems(hours);
        endHourComboBox.setItems(hours);
    }

    private void switchSceneToReportScene(ActionEvent actionEvent){
        switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/admin/Report.fxml");
    }
}
