package presentationLayer.controller.tableInitialization.coustom;

import businessLogic.menu.MenuItemLogic;
import javafx.scene.control.TableView;
import model.dto.menu.MenuItemDTO;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

public class InitializationMenuItemTable extends InitializationTable<MenuItemDTO, MenuItemLogic> {
    public InitializationMenuItemTable(MenuItemLogic logicObject, TableView<MenuItemDTO> table) {
        super(logicObject, table);
    }
}
