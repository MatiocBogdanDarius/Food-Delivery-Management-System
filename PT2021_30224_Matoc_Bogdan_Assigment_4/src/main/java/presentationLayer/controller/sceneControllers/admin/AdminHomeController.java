package presentationLayer.controller.sceneControllers.admin;

import businessLogic.user.UserLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import presentationLayer.controller.switchScene.AdminControlMenuHandle;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController  implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    @FXML
    private Button updateStockButton;
    @FXML
    private Button updateMenuButton;
    @FXML
    private Button createReportsButton;
    @FXML
    private Button createMenuButton;
    @FXML
    private Label nameAdminLabel;
    @FXML
    private Button logOutButton;

    public AdminHomeController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(updateStockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        nameAdminLabel.setText(UserLogic.currentUser.getName());
    }
}
