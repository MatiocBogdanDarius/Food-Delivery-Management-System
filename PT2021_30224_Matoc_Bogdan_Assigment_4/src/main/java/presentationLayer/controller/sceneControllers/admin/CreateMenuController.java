package presentationLayer.controller.sceneControllers.admin;

import businessLogic.menu.MenuLogic;
import businessLogic.menu.ProductLogic;
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
import presentationLayer.controller.switchScene.AdminControlMenuHandle;
import presentationLayer.controller.tableInitialization.coustom.InitializationProductTable;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateMenuController implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    private ProductLogic productLogic;
    private MenuLogic menuLogic;
    private ObservableList<MenuItemDTO> productsAvailableObservableList;
    private ObservableList<MenuItemDTO> newMenuObservableList;
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
    private TableView<MenuItemDTO> productsAvailableTable;
    @FXML
    private TableView<MenuItemDTO> newMenuTable;
    @FXML
    private Button createNewMenuButton;
    @FXML
    private Button addItemInNewMenuButton;
    @FXML
    private Button removeItemInNewMenuButton;
    @FXML
    private Button searchButton;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField newMenuNameLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField searchInputTextField;

    public CreateMenuController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
        productLogic = new ProductLogic();
        menuLogic = new MenuLogic();
        productsAvailableObservableList = FXCollections.observableList(productLogic.findAll());
        newMenuObservableList = FXCollections.observableList(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(homeButton, StockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        messageLabel.setVisible(false);
        setProductsAvailableTable();
        setNewMenuTable();
        addItemInNewMenuButton.setOnAction(this::addItemInNewMenuButtonHandle);
        removeItemInNewMenuButton.setOnAction(this::removeItemInNewMenuButtonHandle);
        createNewMenuButton.setOnAction(this::createNewMenuButtonHandle);
        searchButton.setOnAction(this::searchButtonHandle);
    }

    private void setProductsAvailableTable() {
        InitializationTable initializationTable =
                new InitializationProductTable(productLogic, productsAvailableTable);
        initializationTable.setTableWithNonEditableCell();
    }

    private void setNewMenuTable() {
        InitializationTable initializationTable =
                new InitializationProductTable(productLogic, newMenuTable);
        initializationTable.setTableWithNonEditableCell();
        newMenuTable.setItems(FXCollections.observableList(new ArrayList<>()));
    }

    private void addItemInNewMenuButtonHandle(ActionEvent actionEvent) {
        MenuItemDTO availableProduct = productsAvailableTable.getSelectionModel().getSelectedItem();
        if(availableProduct != null)
            newMenuObservableList.add(availableProduct);
        refresh();
    }

    private void removeItemInNewMenuButtonHandle(ActionEvent actionEvent) {
        MenuItemDTO productToRemoveFromNewMenu = newMenuTable.getSelectionModel().getSelectedItem();
        if(productToRemoveFromNewMenu != null)
            newMenuObservableList.remove(productToRemoveFromNewMenu);
        refresh();
    }

    private void createNewMenuButtonHandle(ActionEvent actionEvent){
        messageLabel.setVisible(true);
       try {
           menuLogic.createMenu(new ArrayList<>(newMenuObservableList), newMenuNameLabel.getText());
           messageLabel.setTextFill(Color.BLACK);
           messageLabel.setText("The menu: \"" + newMenuNameLabel.getText() + "\" has been created.");
           newMenuObservableList = FXCollections.observableList(new ArrayList<>());
           refresh();
       } catch (NullInputExceptions exception) {
           messageLabel.setTextFill(Color.RED);
           messageLabel.setText(exception.getMessage());
       }
    }

    private void searchButtonHandle(ActionEvent actionEvent) {
        productsAvailableObservableList =
                FXCollections.observableList(productLogic.searchStringInTile(searchInputTextField.getText()));
        refresh();
    }

    private void refresh(){
        productsAvailableTable.setItems(productsAvailableObservableList);
        newMenuTable.setItems(newMenuObservableList);
        productsAvailableTable.refresh();
        newMenuTable.refresh();
        updatePriceLabel();
    }

    private void updatePriceLabel(){
        AtomicInteger sum = new AtomicInteger();
        newMenuObservableList.forEach(menuProduct -> sum.addAndGet(menuProduct.getPrice()));
        priceLabel.setText(sum + " RON");
    }

}
