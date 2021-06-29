package presentationLayer.controller.sceneControllers.client;

import businessLogic.user.UserLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.users.Client;
import presentationLayer.controller.switchScene.ClientControlMenuHandle;
import presentationLayer.controller.switchScene.SwitchSceneClass;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfileClientController implements Initializable {
    private ClientControlMenuHandle clientControlMenuHandle;
    @FXML
    private Button clientShopButton;
    @FXML
    private Button clientOrdersHistory;
    @FXML
    private Button profileButton;
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
    @FXML
    private Button backToShoppingButton;
    @FXML
    private Button logOutButton2;

    public ProfileClientController() {
        clientControlMenuHandle = new ClientControlMenuHandle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientControlMenuHandle.addMenuHandle(profileButton, clientShopButton, clientOrdersHistory, logOutButton);
        backToShoppingButton.setOnAction(this::backToShoppingButtonHandle);
        logOutButton2.setOnAction(this::logOutButton2Handle);
        setTextFieldsAndDataPicker();
    }

    private void setTextFieldsAndDataPicker() {
        Client currentClient = (Client) UserLogic.currentUser;
        nameTextField.setText(currentClient.getName());
        nameTextField.setEditable(false);
        emailTextField.setText(currentClient.getEmail());
        emailTextField.setEditable(false);
        passwordTextField.setText(currentClient.getPassword());
        passwordTextField.setEditable(false);
        addressTextField.setText(currentClient.getAddress());
        addressTextField.setEditable(false);
        birthdayDatePickerDate.setValue(LocalDate.parse(currentClient.getBirthdayDate()));
        birthdayDatePickerDate.setEditable(false);
    }

    private void backToShoppingButtonHandle(ActionEvent actionEvent) {
        (new SwitchSceneClass())
                .changeScene(actionEvent, "src/main/java/presentationLayer/view/client/ClientShop.fxml");
    }

    private void logOutButton2Handle(ActionEvent actionEvent) {
        (new SwitchSceneClass()).changeScene(actionEvent, "src/main/java/presentationLayer/view/account/Login.fxml");
    }
}
