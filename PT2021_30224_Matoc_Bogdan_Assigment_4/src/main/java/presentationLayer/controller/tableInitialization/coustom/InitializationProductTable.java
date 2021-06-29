package presentationLayer.controller.tableInitialization.coustom;

import businessLogic.menu.ProductLogic;
import javafx.scene.control.TableView;
import model.dto.menu.MenuItemDTO;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

public class InitializationProductTable extends InitializationTable<MenuItemDTO, ProductLogic> {
    public InitializationProductTable(ProductLogic logicObject, TableView<MenuItemDTO> table) {
        super(logicObject, table);
    }


}
