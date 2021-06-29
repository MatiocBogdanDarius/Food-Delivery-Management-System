package dataAcces.fileWork.csvFile;

import dataAcces.fileWork.csvFile.onGenericObjects.CSV;
import model.entities.menu.Product;

public class ProductCSV extends CSV<Product> {
    public ProductCSV(String filePath) {
        super(filePath);
    }
}
