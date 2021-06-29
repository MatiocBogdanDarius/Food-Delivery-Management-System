package presentationLayer.controller.sceneControllers.admin;

import businessLogic.Download;
import businessLogic.bill.BillLogic;
import businessLogic.order.OrderLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import presentationLayer.controller.switchScene.AdminControlMenuHandle;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    @FXML
    private Button homeButton;
    @FXML
    private Button StockButton;
    @FXML
    private Button updateMenuButton;
    @FXML
    private Button createMenuButton;
    @FXML
    private Button createReportsButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TextArea reportTextArea;
    @FXML
    private Button downloadButton;

    public ReportController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(homeButton, StockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        reportTextArea.setText(OrderLogic.report);
        downloadButton.setOnAction(actionEvent ->
                Download.download(downloadButton.getScene().getWindow(), OrderLogic.report, "Report.txt"));
    }
}
