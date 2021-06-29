package presentationLayer.controller.sceneControllers.admin;

import businessLogic.menu.ProductLogic;
import exceptions.FormatInputException;
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
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;
import presentationLayer.controller.switchScene.AdminControlMenuHandle;
import presentationLayer.controller.tableInitialization.coustom.InitializationProductTable;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    private ProductLogic productLogic;
    private ObservableList<MenuItemDTO> tableContentList;
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
    private Button searchButton;
    @FXML
    private Button logOutButton;
    @FXML
    private TableView<MenuItemDTO> table;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField proteinTextField;
    @FXML
    private TextField caloriesTextField;
    @FXML
    private TextField fatTextField;
    @FXML
    private TextField sodiumTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField searchInputTextField;

    public StockController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
        productLogic = new ProductLogic();
        tableContentList = FXCollections.observableList(productLogic.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(homeButton, StockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        setTable();
        errorLabel.setVisible(false);
        addButton.setOnAction(this::addButtonHandle);
        deleteButton.setOnAction(this::deleteButtonHandle);
        searchButton.setOnAction(this::searchButtonHandle);

    }

    private void setTable() {
        InitializationTable initializationTable = new InitializationProductTable(productLogic, table);
        initializationTable.setTableWithEditableCell();
    }

    private void addButtonHandle(ActionEvent actionEvent) {
        try {
            productLogic.add(
                    nameTextField.getText(),
                    caloriesTextField.getText(),
                    proteinTextField.getText(),
                    fatTextField.getText(),
                    sodiumTextField.getText(),
                    priceTextField.getText()
            );
            refresh();
        } catch (NullInputExceptions | FormatInputException exception) {
            errorLabel.setVisible(true);
            errorLabel.setText(exception.getMessage());
        }
        refresh();
    }

    private void deleteButtonHandle(ActionEvent actionEvent) {
        ProductDTO deletedProduct = (ProductDTO) table.getSelectionModel().getSelectedItem();
        new Thread(() -> productLogic.delete(deletedProduct));
        tableContentList.remove(deletedProduct);
        refresh();
    }

    private void searchButtonHandle(ActionEvent actionEvent) {
        tableContentList = FXCollections.observableList(productLogic.searchStringInTile(searchInputTextField.getText()));
        refresh();
    }

    private void refresh() {
        table.setItems(FXCollections.observableList(tableContentList));
        table.refresh();
    }
}
