package presentationLayer.controller.switchScene;


import javafx.event.ActionEvent;

public interface SwitchSceneInterface {
    void changeScene(ActionEvent actionEvent, String fxmlPath);

    void changeScene(ActionEvent actionEvent, String fxmlPath, int width, int height);
}
