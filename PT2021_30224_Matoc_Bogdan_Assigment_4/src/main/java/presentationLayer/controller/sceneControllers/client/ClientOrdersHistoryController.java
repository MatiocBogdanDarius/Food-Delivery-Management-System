package presentationLayer.controller.sceneControllers.client;

import businessLogic.order.OrderLogic;
import businessLogic.menu.ProductLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.dto.menu.MenuItemDTO;
import model.dto.order.OrderDTO;
import presentationLayer.controller.switchScene.ClientControlMenuHandle;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.tableInitialization.coustom.InitializationOrdersTable;
import presentationLayer.controller.tableInitialization.coustom.InitializationProductTable;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientOrdersHistoryController implements Initializable {
    private ClientControlMenuHandle clientControlMenuHandle;
    private ObservableList<OrderDTO> orderList;
    private ObservableList<MenuItemDTO> buyList;
    private OrderLogic orderLogic;
    @FXML
    private Button clientShopButton;
    @FXML
    private Button clientOrdersHistory;
    @FXML
    private Button profileButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<OrderDTO> ordersTable;
    @FXML
    private Button backToShoppingButton;
    @FXML
    private TableView<MenuItemDTO> buyListTable;

    public ClientOrdersHistoryController() {
        clientControlMenuHandle = new ClientControlMenuHandle();
        orderLogic = new OrderLogic();
        orderList = FXCollections.observableList(orderLogic.findAll());
        buyList = FXCollections.observableList(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientControlMenuHandle.addMenuHandle(profileButton, clientShopButton, clientOrdersHistory, logOutButton);
        setOrdersTable();
        setBuyListTable();
        backToShoppingButton.setOnAction(this::backToShoppingButtonHandle);
    }

    public void setOrdersTable() {
        InitializationTable initializationTable = new InitializationOrdersTable(orderLogic, ordersTable);
        initializationTable.setTableWithNonEditableCell();
        int noColumnsOrdersTable = ordersTable.getColumns().size();
        //delete last Columns(client)
        ordersTable.getColumns().remove(noColumnsOrdersTable - 1);
        ordersTable.getColumns().forEach(column -> {
            if (column.getText().equals("clientName")){
                column.setText("Client");
            }
        });
        ordersTable.setOnMouseClicked(this::ordersTableHandle);
    }

    private void ordersTableHandle(MouseEvent mouseEvent) {
        OrderDTO selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        buyList = FXCollections.observableList(orderLogic.getBuyList(selectedOrder));
        refresh();
    }

    private void setBuyListTable() {
        InitializationTable initializationTable = new InitializationProductTable(new ProductLogic(), buyListTable);
        initializationTable.setTableWithEditableCell();
        refresh();
    }

    private void backToShoppingButtonHandle(ActionEvent actionEvent) {
        (new SwitchSceneClass())
                .changeScene(actionEvent, "src/main/java/presentationLayer/view/client/ClientShop.fxml");
    }

    private void refresh(){
        buyListTable.setItems(buyList);
        buyListTable.refresh();
    }
}
