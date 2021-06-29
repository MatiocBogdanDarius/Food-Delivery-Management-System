package model.entities.menu;

import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public MenuItem() {
    }

    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public static List<MenuItem> convertListMenuItemDTOToListOfMenuItem(List<MenuItemDTO> menuItemDTOList) {
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemDTOList.forEach(menuItem -> {
            if(menuItem instanceof MenuDTO)
                menuItemList.add(new Menu((MenuDTO) menuItem));
            else
                menuItemList.add(new Product((ProductDTO) menuItem));
        });
        return menuItemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}
