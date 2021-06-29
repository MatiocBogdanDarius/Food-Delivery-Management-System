package presentationLayer.controller.switchScene;

import javafx.scene.control.Button;

public class AdminControlMenuHandle {
    private SwitchSceneInterface switchScene;

    public AdminControlMenuHandle(){
        switchScene = new SwitchSceneClass();
    }

    public void addMenuHandle(Button updateStockButton, Button updateMenuButton, Button createMenuButton,
                           Button createReportsButton, Button logOutButton){
        updateStockButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/admin/Stock.fxml"));
        updateMenuButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/admin/UpdateMenu.fxml"));
        createMenuButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/admin/CreateMenu.fxml"));
        createReportsButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/admin/CreateReports.fxml"));
        logOutButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/account/Login.fxml"));
    }

    public void addMenuHandle(Button homeButton, Button updateStockButton, Button updateMenuButton, Button createMenuButton,
                              Button createReportsButton, Button logOutButton){
        addMenuHandle(updateStockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        homeButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/admin/AdminHome.fxml"));
    }
}
