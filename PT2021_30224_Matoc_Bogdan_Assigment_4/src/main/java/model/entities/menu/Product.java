package model.entities.menu;

import com.opencsv.bean.CsvBindByPosition;
import model.dto.menu.ProductDTO;
import model.entities.menu.MenuItem;

import java.io.Serializable;

/**
 * it is used to be able to manipulate and store the data resulting from the database query
 * corresponds to the product table
 */
public class Product extends MenuItem implements Serializable {
    @CsvBindByPosition(position = 0)
    private String title;

    @CsvBindByPosition(position = 1)
    private double rating;

    @CsvBindByPosition(position = 2)
    private int calories;

    @CsvBindByPosition(position = 3)
    private int protein;

    @CsvBindByPosition(position = 4)
    private int fat;

    @CsvBindByPosition(position = 5)
    private int sodium;

    @CsvBindByPosition(position = 6)
    private int price;

    public Product() {
        super();
    }

    public Product(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public Product(ProductDTO productDTO) {
        this(
                productDTO.getTitle(),
                productDTO.getRating(),
                productDTO.getCalories(),
                productDTO.getProtein(),
                productDTO.getFat(),
                productDTO.getSodium(),
                productDTO.getPrice()
        );
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
    public boolean equals(Object obj) {
        if(!(obj instanceof Product))
            return  false;
        Product param = (Product) obj;
        return  getTitle().equals(param.getTitle()) && getCalories() == param.getCalories()
                && getProtein() == param.getProtein() && getFat() == param.getFat() && getSodium() == param.getSodium();
    }

    @Override
    public int hashCode() {
        if(title != null)
            return title.hashCode();
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + super.getTitle() + '\'' +
                ", rating=" + super.getRating() +
                ", calories=" + super.getCalories() +
                ", protein=" + super.getProtein() +
                ", fat=" + super.getFat() +
                ", sodium=" + super.getSodium() +
                ", price=" + super.getPrice() +
                '}';
    }


}
