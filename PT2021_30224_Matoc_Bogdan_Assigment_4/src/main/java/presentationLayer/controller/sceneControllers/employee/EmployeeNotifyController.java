package presentationLayer.controller.sceneControllers.employee;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeNotifyController implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            URL url = new File("src/main/java/presentationLayer/view/employee/EmployeeNotify.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage employeeStage = new Stage();
            employeeStage.setTitle("Employee Application");
            Scene scene = new Scene(root);
            employeeStage.setScene(scene);
            employeeStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
