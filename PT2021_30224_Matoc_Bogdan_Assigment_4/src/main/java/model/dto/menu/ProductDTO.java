package model.dto.menu;

import model.entities.menu.Product;

/**
 * this class is used to transfer a product's data between the presentation layer and the business Logic layer
 */
public class ProductDTO extends MenuItemDTO {
    public ProductDTO() {
        super();
    }

    public ProductDTO(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public ProductDTO(Product product){
        this(
                product.getTitle(),
                product.getRating(),
                product.getCalories(),
                product.getProtein(),
                product.getFat(),
                product.getSodium(),
                product.getPrice()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ProductDTO))
            return false;
        ProductDTO product = (ProductDTO) obj;

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "title=" + getTitle() +
                ", rating=" + getRating() +
                ", calories=" + getCalories() +
                ", protein=" + getProtein() +
                ", fat=" + getFat() +
                ", sodium=" + getSodium() +
                ", price=" + getPrice() +
                '}';
    }
}
