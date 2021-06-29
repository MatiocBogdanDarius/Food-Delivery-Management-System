package presentationLayer.controller.sceneControllers.employee;

import businessLogic.user.UserLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.users.Employee;
import presentationLayer.controller.switchScene.SwitchSceneClass;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfileEmployeeController implements Initializable {
    @FXML
    private Button logOutButton;
    @FXML
    private DatePicker birthdayDatePickerDate;
    @FXML
    private TextField emailTextField;
    @FXML
    private  TextField passwordTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField nameTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logOutButton.setOnAction(this::logOutButtonHandle);
        setTextFieldsAndDataPicker();
    }

    private void setTextFieldsAndDataPicker() {
        Employee currentEmployee = (Employee) UserLogic.currentUser;
        nameTextField.setText(currentEmployee.getName());
        nameTextField.setEditable(false);
        emailTextField.setText(currentEmployee.getEmail());
        emailTextField.setEditable(false);
        passwordTextField.setText(currentEmployee.getPassword());
        passwordTextField.setEditable(false);
        addressTextField.setText(currentEmployee.getAddress());
        addressTextField.setEditable(false);
        birthdayDatePickerDate.setValue(LocalDate.parse(currentEmployee.getBirthdayDate()));
        birthdayDatePickerDate.setEditable(false);
    }

    private void logOutButtonHandle(ActionEvent actionEvent) {
        (new SwitchSceneClass()).changeScene(actionEvent, "src/main/java/presentationLayer/view/account/Login.fxml");
    }
}
