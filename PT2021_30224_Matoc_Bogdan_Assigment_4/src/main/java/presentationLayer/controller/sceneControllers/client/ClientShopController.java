package presentationLayer.controller.sceneControllers.client;

import businessLogic.order.OrderLogic;
import businessLogic.menu.MenuItemLogic;
import exceptions.NullInputExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.dto.menu.MenuItemDTO;
import presentationLayer.controller.sceneControllers.employee.EmployeeNotifyController;
import presentationLayer.controller.switchScene.ClientControlMenuHandle;
import presentationLayer.controller.switchScene.SwitchSceneClass;
import presentationLayer.controller.tableInitialization.coustom.InitializationMenuItemTable;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientShopController implements Initializable {
    private ClientControlMenuHandle clientControlMenuHandle;
    private MenuItemLogic menuItemLogic;
    private ObservableList<MenuItemDTO> menuList;
    private ObservableList<MenuItemDTO> orderProductList;
    private OrderLogic orderLogic;
    private EmployeeNotifyController employeeController;
    @FXML
    private Button clientShopButton;
    @FXML
    private Button clientOrdersHistory;
    @FXML
    private Button profileButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<MenuItemDTO> menuTable;
    @FXML
    private TableView<MenuItemDTO> orderTable;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button addItemInOrderButton;
    @FXML
    private Button removeItemInOrderButton;
    @FXML
    private TextField searchInputTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label priceLabel;
    @FXML
    private Label errorLabel;

    public ClientShopController() {
        clientControlMenuHandle = new ClientControlMenuHandle();
        menuItemLogic = new MenuItemLogic();
        orderLogic = new OrderLogic();
        menuList = FXCollections.observableList(menuItemLogic.findAll());
        orderProductList = FXCollections.observableList(new ArrayList<>());
        employeeController = new EmployeeNotifyController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientControlMenuHandle.addMenuHandle(profileButton, clientShopButton, clientOrdersHistory, logOutButton);
        errorLabel.setVisible(false);
        setMenuTable();
        setOrderTable();
        searchButton.setOnAction(this::searchButtonHandle);
        addItemInOrderButton.setOnAction(this::addItemInOrderButtonHandle);
        removeItemInOrderButton.setOnAction(this::removeItemInOrderButtonHandle);
        placeOrderButton.setOnAction(this::placeOrderButtonHandle);
    }

    private void setMenuTable() {
        InitializationTable initializationTable = new InitializationMenuItemTable(menuItemLogic, menuTable);
        initializationTable.setTableWithEditableCell();
    }

    private void setOrderTable() {
        InitializationTable initializationTable = new InitializationMenuItemTable(menuItemLogic, orderTable);
        initializationTable.setTableWithEditableCell();
        refresh();
    }

    private void searchButtonHandle(ActionEvent actionEvent) {
        menuList = FXCollections.observableList(menuItemLogic.searchStringInTile(searchInputTextField.getText()));
        menuTable.setItems(menuList);
        menuTable.refresh();
    }

    private void addItemInOrderButtonHandle(ActionEvent actionEvent) {
        MenuItemDTO availableProductOrMenu = menuTable.getSelectionModel().getSelectedItem();
        if(availableProductOrMenu != null)
            orderProductList.add(availableProductOrMenu);
        refresh();
    }

    private void removeItemInOrderButtonHandle(ActionEvent actionEvent) {

        MenuItemDTO productToRemoveFromNewMenu = orderTable.getSelectionModel().getSelectedItem();
        if(productToRemoveFromNewMenu != null)
            orderProductList.remove(productToRemoveFromNewMenu);
        refresh();
    }

    private void placeOrderButtonHandle(ActionEvent actionEvent) {
        try {
            orderLogic.addPropertyChangeListener(employeeController);
            orderLogic.placeOrder(orderProductList);
            (new SwitchSceneClass()).changeScene(actionEvent, "src/main/java/presentationLayer/view/bill/Bill.fxml");
        } catch (NullInputExceptions exception) {
            errorLabel.setVisible(true);
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(exception.getMessage());
        }
    }

    private void refresh() {
        orderTable.setItems(FXCollections.observableList(orderProductList));
        orderTable.refresh();
        updatePriceLabel();
    }

    private void updatePriceLabel(){
        AtomicInteger sum = new AtomicInteger(0);
        orderProductList.forEach(menuOrProduct -> sum.addAndGet(menuOrProduct.getPrice()));
        priceLabel.setText(sum.get() + " RON");
    }
}
