package presentationLayer.controller.tableInitialization.coustom;

import businessLogic.menu.MenuLogic;

import javafx.scene.control.TableView;
import model.dto.menu.MenuItemDTO;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;


public class InitializationMenuTable extends InitializationTable<MenuItemDTO, MenuLogic> {
    public InitializationMenuTable(MenuLogic logicObject, TableView<MenuItemDTO> table) {
        super(logicObject, table);
    }

}
