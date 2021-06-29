package presentationLayer.controller.sceneControllers.account;

import businessLogic.account.CreateAccountLogic;
import exceptions.IncompatiblePasswordsException;
import exceptions.InvalidBirthdayDateExceptions;
import exceptions.NullInputExceptions;
import exceptions.UnavailableEmailException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.switchScene.SwitchSceneInterface;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    private CreateAccountLogic createAccountLogic;
    private SwitchSceneInterface switchScene;
    @FXML
    private DatePicker birthdayDatePickerDate;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button backToLoginButton;

    public CreateAccountController() {
        createAccountLogic = new CreateAccountLogic();
        switchScene = new SwitchSceneClass();
        birthdayDatePickerDate = new DatePicker();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createAccountButton.setOnAction(this::createAccountButtonHandle);
        backToLoginButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/account/Login.fxml"));
    }

    private void createAccountButtonHandle(ActionEvent actionEvent){
        try{
            createAccountLogic.createAccount(
                    nameTextField.getText().trim(),
                    emailTextField.getText().trim(),
                    passwordField.getText(),
                    confirmPasswordField.getText(),
                    addressTextField.getText(),
                    birthdayDatePickerDate.getValue()
            );

            switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/client/ProfileClient.fxml");
        } catch (InvalidBirthdayDateExceptions | NullInputExceptions | UnavailableEmailException | IncompatiblePasswordsException exception) {
            errorLabel.setText(exception.getMessage());
        }
    }
}
