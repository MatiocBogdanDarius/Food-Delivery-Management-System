package businessLogic.iDeliveryServiceProcessing;

import businessLogic.menu.MenuLogic;
import businessLogic.menu.ProductLogic;
import model.dto.menu.MenuItemDTO;

import java.util.ArrayList;
import java.util.List;

public interface IMenuItemProcessing {
    /**
     * @pre inputText != null
     * @post @result != null
     */
    List<MenuItemDTO> searchStringInTile(String inputText);
}
