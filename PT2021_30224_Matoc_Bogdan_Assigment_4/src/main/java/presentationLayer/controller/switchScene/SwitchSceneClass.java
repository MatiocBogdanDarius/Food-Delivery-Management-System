package presentationLayer.controller.switchScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SwitchSceneClass implements SwitchSceneInterface {
    @Override
    public void changeScene(ActionEvent actionEvent, String fxmlPath){

        try {
            URL url = new File(fxmlPath).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void changeScene(ActionEvent actionEvent, String fxmlPath, int width, int height){
        try {
            URL url = new File(fxmlPath).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setWidth(width);
            stage.setHeight(height);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
