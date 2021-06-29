package businessLogic.menu;


import businessLogic.iDeliveryServiceProcessing.IProductProcessing;
import businessLogic.menu.MenuItemLogic;
import dataAcces.operations.onCustomObjects.ProductDAO;
import exceptions.FormatInputException;
import exceptions.NullInputExceptions;
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;
import model.entities.menu.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class contains the logic part on a product
 */
public class ProductLogic extends MenuItemLogic implements IProductProcessing {
    private static List<MenuItemDTO> productList;
    private ProductDAO productDAO;

    public ProductLogic(){
        productDAO = new ProductDAO();
        findAll();
    }

    @Override
    public List<MenuItemDTO> findAll(){
        if(productList != null)
            return productList;
        List<Product> products =  productDAO.findAll();
        productList = new ArrayList<>();
        productList = products.stream().map(ProductDTO::new).collect(Collectors.toList());
        return productList;
    }

    public List<MenuItemDTO> searchStringInTile(String inputText) {
        return searchStringInTileInList(inputText, productList);
    }

    public void add(String title, String calories, String protein, String fat, String sodium, String price)
            throws NullInputExceptions, FormatInputException {

        if(title == null || calories == null || protein == null || fat == null || sodium == null || price == null)
            throw new NullInputExceptions("All fields must be filled in before introducing a new product!");
        try{
            add(new ProductDTO(
                    title,
                    0L,
                    Integer.parseInt(calories),
                    Integer.parseInt(protein),
                    Integer.parseInt(fat),
                    Integer.parseInt(sodium),
                    Integer.parseInt(price)
            ));
        } catch (NumberFormatException exception) {
            throw new FormatInputException(
                    "fields: calories, protein, fat, sodium and price must be completed before introducing a new product");
        }
    }

    public void delete(ProductDTO product) {
        productList.remove(product);
        productDAO.delete(new Product(product));
    }

    private void add(ProductDTO product) {
        productList.add(product);
        productDAO.add(new Product(product));
    }

}
