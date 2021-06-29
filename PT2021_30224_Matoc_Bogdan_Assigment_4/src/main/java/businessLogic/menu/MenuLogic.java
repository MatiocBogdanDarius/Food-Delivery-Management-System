package businessLogic.menu;

import businessLogic.iDeliveryServiceProcessing.IMenuProcessing;
import businessLogic.menu.MenuItemLogic;
import dataAcces.operations.onCustomObjects.MenuDAO;
import dataAcces.operations.onGenericObjects.DAO;
import exceptions.NullInputExceptions;
import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;
import model.entities.menu.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @invariant isWellFormed()
 */
public class MenuLogic extends MenuItemLogic implements IMenuProcessing {
    private static List<MenuItemDTO> menuList;
    private MenuDAO menuDAO;

    public MenuLogic() {
        menuDAO = new MenuDAO();
        findAll();
    }

    @Override
    public List<MenuItemDTO> findAll() {
        if(menuList != null)
            return menuList;
        List<Menu> menus = menuDAO.findAll();
        menuList = menus.stream().map(MenuDTO::new).collect(Collectors.toList());
        return menuList;

    }

    public void createMenu(List<MenuItemDTO> newMenuList, String newMenuName) throws NullInputExceptions {
        if(newMenuList == null || newMenuList.isEmpty())
            throw new NullInputExceptions("The menu must contain one or more products!");
        if(newMenuName == null || newMenuName.isEmpty())
            throw new NullInputExceptions("The menu must have a name");
        Menu newMenu = new Menu();
        newMenuList.forEach(menuItem -> {
            newMenu.setCalories(newMenu.getCalories() + menuItem.getCalories());
            newMenu.setProtein(newMenu.getProtein() + menuItem.getProtein());
            newMenu.setFat(newMenu.getFat() + menuItem.getFat());
            newMenu.setSodium(newMenu.getSodium() + menuItem.getSodium());
            newMenu.setPrice(newMenu.getPrice() + menuItem.getPrice());
        });
        newMenu.setTitle(newMenuName);
        newMenu.setMenuItems(newMenuList);
        add(newMenu);
    }

    public void delete(MenuDTO menuDTO) {
        menuList.remove(menuDTO);
        menuDAO.delete(new Menu(menuDTO));
    }

    public List<MenuItemDTO> searchStringInTile(String inputText) {
        List<MenuItemDTO> allMenu = findAll();
        return searchStringInTileInList(inputText, allMenu);
    }

    private void add(Menu menu){
        menuList.add(new MenuDTO(menu));
        menuDAO.add(menu);
    }

    protected boolean isWellFormed(){
        if(menuList == null)
            return false;
        for(MenuItemDTO menuItemDTO: menuList){
            if(menuList.stream().filter(menuItemDTO1 -> menuItemDTO == menuItemDTO1).count() != 1)
                return false;
        }
        return true;
    }
}
