package presentationLayer.controller.tableInitialization.coustom;

import businessLogic.order.OrderLogic;
import javafx.scene.control.TableView;
import model.dto.order.OrderDTO;
import presentationLayer.controller.tableInitialization.genric.InitializationTable;

public class InitializationOrdersTable extends InitializationTable<OrderDTO, OrderLogic> {
    public InitializationOrdersTable(OrderLogic logicObject, TableView<OrderDTO> table) {
        super(logicObject, table);
    }
}
