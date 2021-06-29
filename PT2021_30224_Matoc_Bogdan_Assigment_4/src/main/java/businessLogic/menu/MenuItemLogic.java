package businessLogic.menu;

import businessLogic.BusinessLogic;
import businessLogic.iDeliveryServiceProcessing.IMenuItemProcessing;
import model.dto.menu.MenuItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemLogic implements BusinessLogic<MenuItemDTO>, IMenuItemProcessing {
    @Override
    public List<MenuItemDTO> findAll() {
        List<MenuItemDTO> result = new ArrayList<>();
        result.addAll((new MenuLogic()).findAll());
        result.addAll((new ProductLogic()).findAll());
        return result;
    }

    public List<MenuItemDTO> searchStringInTile(String inputText) {
        List<MenuItemDTO> result = new ArrayList<>();
        result.addAll((new MenuLogic()).searchStringInTile(inputText));
        result.addAll((new ProductLogic()).searchStringInTile(inputText));
        return result;
    }

    /**
     * @pre inputText != null
     * @pre menuItemDTOList != null
     * @post @result != null
     */
    protected List<MenuItemDTO> searchStringInTileInList(String inputText, List<MenuItemDTO> menuItemDTOList) {
        return menuItemDTOList.stream()
                .filter(menuItemDTO -> menuItemDTO.getTitle() != null)
                .filter(menuItemDTO -> menuItemDTO.getTitle().toLowerCase().trim().contains(inputText.toLowerCase().trim()))
                .collect(Collectors.toList());
    }
}
