package model.dto.menu;

import model.entities.menu.Menu;
import model.entities.menu.Product;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO extends MenuItemDTO {
    private List<MenuItemDTO> menuItemsDto;

    public MenuDTO(){
        super();
        menuItemsDto = new ArrayList<>();
    }

    public MenuDTO(List<MenuItemDTO> menuItemsDto) {
        this();
        this.menuItemsDto = menuItemsDto;
    }

    public MenuDTO(String title, double rating, int calories, int protein, int fat, int sodium, int price, List<MenuItemDTO> menuItemsDto) {
        super(title, rating, calories, protein, fat, sodium, price);
        this.menuItemsDto = menuItemsDto;
    }

    public MenuDTO(Menu menu){
        super(
                menu.getTitle(),
                menu.getRating(),
                menu.getCalories(),
                menu.getProtein(),
                menu.getFat(),
                menu.getSodium(),
                menu.getPrice()
        );

        menuItemsDto = new ArrayList<>();
        menu.getMenuItems().forEach(menuItem -> {
            MenuItemDTO newMenuItemDTO;
            if(menuItem instanceof Product)
                newMenuItemDTO = new ProductDTO((Product) menuItem);
            else
                newMenuItemDTO = new MenuDTO((Menu) menuItem);

            menuItemsDto.add(newMenuItemDTO);
        });

    }

    public List<MenuItemDTO> getMenuItemsDto() {
        return menuItemsDto;
    }

    public void setMenuItemsDto(List<MenuItemDTO> menuItemsDto) {
        this.menuItemsDto = menuItemsDto;
    }

    @Override
    public String toString()  {
        StringBuilder result = new StringBuilder();
        result.append("MenuDTO{" + "title=")
                .append(getTitle())
                .append(", rating=")
                .append(getRating())
                .append(", calories=")
                .append(getCalories())
                .append(", protein=")
                .append(getProtein())
                .append(", fat=")
                .append(getFat())
                .append(", sodium=")
                .append(getSodium())
                .append(", price=")
                .append(getPrice())
                .append("}\n\tmenu items:");
        menuItemsDto.forEach(menuItemDTO -> result.append("\n\t\t").append(menuItemDTO.toString()));
        return result.toString();
    }
}
