package businessLogic.iDeliveryServiceProcessing;

import exceptions.FormatInputException;
import exceptions.NullInputExceptions;
import model.dto.menu.ProductDTO;
import model.entities.menu.Product;

public interface IProductProcessing {
    /**
     * @pre ProductLogic.productList != null
     * @post ProductLogic.productList == ProductLogic.productList@pre + 1
     * @throws NullInputExceptions
     * @throws FormatInputException
     */
    void add(String title, String calories, String protein, String fat, String sodium, String price)
            throws NullInputExceptions, FormatInputException;

    /**
     * @pre ProductLogic.productList != null
     * @pre menuDTO != null
     * @pre ProductLogic.productList.contains(menuDTO)
     * @post ProductLogic.productList == ProductLogic.productList@pre - 1
     * @throws NullInputExceptions
     */
    void delete(ProductDTO product);
}
