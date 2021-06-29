package presentationLayer.controller.sceneControllers.account;

import businessLogic.account.LoginLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.switchScene.SwitchSceneInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoginLogic loginLogic;
    private SwitchSceneInterface switchScene;
    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button createNewAccountButton;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailTextField;

    public LoginController() {
        loginLogic = new LoginLogic();
        switchScene = new SwitchSceneClass();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLabel.setVisible(false);
        loginButton.setOnAction(this::loginButtonHandle);
        createNewAccountButton.setOnAction(actionEvent -> switchScene
                .changeScene(actionEvent, "src/main/java/presentationLayer/view/account/CreateAccount.fxml"));
    }

    private void loginButtonHandle(ActionEvent actionEvent) {
        String email = emailTextField.getText().trim();
        String password = passwordField.getText();
        switch (loginLogic.login(email, password)) {
            case ADMIN -> switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/admin/AdminHome.fxml");
            case EMPLOYEE -> switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/employee/ProfileEmployee.fxml");
            case CLIENT -> switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/client/ClientShop.fxml");
            default -> errorLabel.setVisible(true);
        }

    }
}
