package presentationLayer.controller.sceneControllers.admin;

import businessLogic.menu.MenuLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import presentationLayer.controller.switchScene.AdminControlMenuHandle;
import presentationLayer.controller.tableInitialization.coustom.InitializationMenuTable;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateMenuController implements Initializable {
    private AdminControlMenuHandle adminControlMenuHandle;
    private MenuLogic menuLogic;
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
    private Button logOutButton;
    @FXML
    private TableView<MenuItemDTO> table;
    @FXML
    private Button searchButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField searchInputTextField;

    public UpdateMenuController() {
        adminControlMenuHandle = new AdminControlMenuHandle();
        menuLogic = new MenuLogic();
        tableContentList = FXCollections.observableList(new ArrayList<>());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlMenuHandle
                .addMenuHandle(homeButton, StockButton, updateMenuButton, createMenuButton, createReportsButton, logOutButton);
        setTable();
        deleteButton.setOnAction(this::deleteButtonHandle);
        searchButton.setOnAction(this::searchButtonHandle);

    }

    private void setTable() {
        InitializationTable initializationTable = new InitializationMenuTable(menuLogic, table);
        initializationTable.setTableWithEditableCell();
    }

    private void deleteButtonHandle(ActionEvent actionEvent) {
        menuLogic.delete((MenuDTO) table.getSelectionModel().getSelectedItem());
        refresh();
    }

    private void searchButtonHandle(ActionEvent actionEvent) {
        tableContentList = FXCollections.observableList(menuLogic.searchStringInTile(searchInputTextField.getText()));
        refresh();
    }

    private void refresh() {
        tableContentList = FXCollections.observableList(menuLogic.findAll());
        table.setItems(FXCollections.observableList(tableContentList));
    }

}
