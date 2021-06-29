package presentationLayer.controller.sceneControllers.bill;

import businessLogic.Download;
import businessLogic.bill.BillLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.switchScene.SwitchSceneInterface;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *this class contains the initialization of the interface Bill scene and adds event hendle on buttons
 */
public class BillController implements Initializable {
    private SwitchSceneInterface switchScene;
    private BillLogic billLogic;
    @FXML
    private TextArea billMessageTextArea;
    @FXML
    private Button backToShoppingButton;
    @FXML
    private Button downloadButton;

    public BillController() {
        this.billLogic = new BillLogic();
        this.switchScene = new SwitchSceneClass();
        this.backToShoppingButton = new Button();
        this.downloadButton = new Button();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToShoppingButton.setOnAction(actionEvent ->
                switchScene.changeScene(actionEvent, "src/main/java/presentationLayer/view/client/ClientShop.fxml"));
        billMessageTextArea.setText(billLogic.createBill());
        downloadButton.setOnAction(actionEvent ->
                Download.download(downloadButton.getScene().getWindow(), BillLogic.billString, "Bill.txt"));
    }
}
