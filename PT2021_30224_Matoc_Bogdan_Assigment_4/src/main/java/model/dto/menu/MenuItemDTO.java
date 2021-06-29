package model.dto.menu;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.dto.DTO;
import model.entities.menu.Menu;
import model.entities.menu.MenuItem;
import model.entities.menu.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItemDTO implements DTO {
    private SimpleStringProperty title;
    private SimpleDoubleProperty rating;
    private SimpleIntegerProperty calories;
    private SimpleIntegerProperty protein;
    private SimpleIntegerProperty fat;
    private SimpleIntegerProperty sodium;
    private SimpleIntegerProperty price;

    public MenuItemDTO() {
        this.title = new SimpleStringProperty();
        this.rating = new SimpleDoubleProperty();
        this.calories = new SimpleIntegerProperty();
        this.protein = new SimpleIntegerProperty();
        this.fat = new SimpleIntegerProperty();
        this.sodium = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
    }

    public MenuItemDTO(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this();
        this.title.set(title);
        this.rating.set(rating);
        this.calories.set(calories);
        this.protein.set(protein);
        this.fat.set(fat);
        this.sodium.set(sodium);
        this.price.set(price);
    }

    public static List<MenuItemDTO> convertListMenuItemToListOfMenuItemDTO(List<MenuItem> menuItemList) {
        List<MenuItemDTO> menuItemDTOList = new ArrayList<>();
        menuItemList.forEach(menuItem -> {
            if(menuItem instanceof Menu)
                menuItemDTOList.add(new MenuDTO((Menu) menuItem));
            else
                menuItemDTOList.add(new ProductDTO((Product) menuItem));
        });
        return menuItemDTOList;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public double getRating() {
        return rating.get();
    }

    public void setRating(double rating) {
        this.rating.set(rating);
    }

    public int getCalories() {
        return calories.get();
    }

    public void setCalories(int calories) {
        this.calories.set(calories);
    }

    public int getProtein() {
        return protein.get();
    }

    public void setProtein(int protein) {
        this.protein.set(protein);
    }

    public int getFat() {
        return fat.get();
    }

    public void setFat(int fat) {
        this.fat.set(fat);
    }

    public int getSodium() {
        return sodium.get();
    }

    public void setSodium(int sodium) {
        this.sodium.set(sodium);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    @Override
    public int hashCode() {
        if(title.get() == null)
            return 0;
        return title.get().hashCode();
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "title=" + title +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}
