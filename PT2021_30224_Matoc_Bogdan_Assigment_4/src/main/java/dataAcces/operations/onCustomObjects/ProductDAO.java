package dataAcces.operations.onCustomObjects;

import dataAcces.fileWork.csvFile.ProductCSV;
import dataAcces.fileWork.serializationObjectsContentFile.OperationOnFile;
import dataAcces.operations.onGenericObjects.DAO;
import model.entities.menu.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * this class is an instantaneous class that extends the DAO class;
 * at the moment it is empty but in view of a possible extension of the project it can be populated with methods
 * useful for accessing the buyLIst table in the database
 */
public class ProductDAO extends DAO<Product> {
    public ProductDAO() {
        super("src/main/resources/products.txt");
    }

    @Override
    public List<Product> findAll(){
        if(super.findAll().isEmpty()){
            ProductCSV productCSV = new ProductCSV("src/main/resources/products.csv");
            OperationOnFile<Product> productOperationOnFile =
                    new OperationOnFile<>(Product.class, "src/main/resources/products.txt");
            try {
                productOperationOnFile.write(productCSV.readFromCSVFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findAll();
    }
}
