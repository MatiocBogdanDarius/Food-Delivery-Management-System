package businessLogic.iDeliveryServiceProcessing;

import exceptions.NullInputExceptions;
import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import model.entities.menu.Menu;

import java.util.List;

public interface IMenuProcessing {
    /**
     * @pre MenuLogic.menuList != null
     * @post MenuLogic.menuList == MenuLogic.menuList@pre + 1
     * @throws NullInputExceptions
     */
    void createMenu(List<MenuItemDTO> newMenuList, String newMenuName) throws NullInputExceptions;

    /**
     * @pre MenuLogic.menuList != null
     * @pre menuDTO != null
     * @pre MenuLogic.menuList.contains(menuDTO)
     * @post MenuLogic.menuList == MenuLogic.menuList@pre - 1
     * @throws NullInputExceptions
     */
    void delete(MenuDTO menuDTO);
}
