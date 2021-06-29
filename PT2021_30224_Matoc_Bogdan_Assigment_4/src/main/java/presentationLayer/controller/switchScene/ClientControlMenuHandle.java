package presentationLayer.controller.switchScene;

import javafx.scene.control.Button;

public class ClientControlMenuHandle {
    private SwitchSceneInterface switchScene;

    public ClientControlMenuHandle() {
        this.switchScene = new SwitchSceneClass();
    }

    public void addMenuHandle(Button profileButton, Button clientShopButton, Button clientOrdersHistory, Button logOutButton){
        profileButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/client/ProfileClient.fxml"));
        clientShopButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/client/ClientShop.fxml"));
        clientOrdersHistory.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/client/ClientOrdersHistory.fxml"));
        logOutButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent,"src/main/java/presentationLayer/view/account/Login.fxml"));
    }
}
