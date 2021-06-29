package model.entities.menu;

import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuItem implements Serializable {
    private List<MenuItem> menuItems;

    public Menu() {
        super();
        menuItems = new ArrayList<>();
    }

    public Menu(List<MenuItem> menuItems) {
        this();
        this.menuItems = menuItems;
    }

    public Menu(String title, double rating, int calories, int protein, int fat, int sodium, int price, List<MenuItem> menuItems) {
        super(title, rating, calories, protein, fat, sodium, price);
        this.menuItems = menuItems;
    }

    public Menu(MenuDTO menuDTO){
        super(
                menuDTO.getTitle(),
                menuDTO.getRating(),
                menuDTO.getCalories(),
                menuDTO.getProtein(),
                menuDTO.getFat(),
                menuDTO.getSodium(),
                menuDTO.getPrice()
        );
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> newMenuList) {
        menuItems = new ArrayList<>();
        newMenuList.forEach(menuItemDTO -> {
            if(menuItemDTO instanceof ProductDTO)
                menuItems.add(new Product((ProductDTO) menuItemDTO));
            else
                menuItems.add(new Menu((MenuDTO) menuItemDTO));
        });
    }

    public Menu(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public double getRating() {
        return super.getRating();
    }

    @Override
    public void setRating(double rating) {
        super.setRating(rating);
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

    @Override
    public void setCalories(int calories) {
        super.setCalories(calories);
    }

    @Override
    public int getProtein() {
        return super.getProtein();
    }

    @Override
    public void setProtein(int protein) {
        super.setProtein(protein);
    }

    @Override
    public int getFat() {
        return super.getFat();
    }

    @Override
    public void setFat(int fat) {
        super.setFat(fat);
    }

    @Override
    public int getSodium() {
        return super.getSodium();
    }

    @Override
    public void setSodium(int sodium) {
        super.setSodium(sodium);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "title='" + getTitle() +
                ", rating=" + getRating() +
                ", calories=" + getCalories() +
                ", protein=" + getProtein() +
                ", fat=" + getFat() +
                ", sodium=" + getSodium() +
                ", price=" + getPrice() +
                ", menuItems=" + menuItems.toString() +
                '}';
    }
}
