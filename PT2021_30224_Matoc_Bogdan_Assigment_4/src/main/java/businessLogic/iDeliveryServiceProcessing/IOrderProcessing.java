package businessLogic.iDeliveryServiceProcessing;

import exceptions.FormatInputException;
import exceptions.IncorrectStartEndHourException;
import exceptions.NullInputExceptions;
import model.dto.menu.MenuItemDTO;
import model.dto.order.OrderDTO;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

public interface IOrderProcessing {
    /**
     * @pre OrderLogic.orderDTOList != null
     * @post @result != null
     */
    OrderDTO findLast();

    /**
     * @pre buyList != null
     * @post OrderLogic.orderDTOList == OrderLogic.orderDTOList@pre + 1
     * @post OrderLogic.orderListHashMap == OrderLogic.orderListHashMap@pre + 1
     * @throws NullInputExceptions
     */
    void placeOrder(List<MenuItemDTO> buyList) throws NullInputExceptions;

    /**
     * @pre OrderLogic.orderDTOList != null
     * @pre OrderLogic.orderListHashMap != null
     * @post OrderLogic.report != null
     * @throws NullInputExceptions
     * @throws IncorrectStartEndHourException
     */
    void getOrdersFromInterval(LocalDate date, Integer startHour, Integer endHour) throws NullInputExceptions, IncorrectStartEndHourException;

    /**
     * @pre OrderLogic.orderDTOList != null
     * @pre OrderLogic.orderListHashMap != null
     * @post OrderLogic.report != null
     * @throws NullInputExceptions
     * @throws FormatInputException
     */
    void getTopProduct(String noMinOfPurchasesOfProductString) throws NullInputExceptions, FormatInputException;

    /**
     * @pre OrderLogic.orderDTOList != null
     * @pre OrderLogic.orderListHashMap != null
     * @post OrderLogic.report != null
     * @throws NullInputExceptions
     * @throws FormatInputException
     */
    void getDayReport(LocalDate date, String noMinOfPurchasesOfProductString) throws NullInputExceptions, FormatInputException;

    /**
     * @pre OrderLogic.orderDTOList != null
     * @pre OrderLogic.orderListHashMap != null
     * @post OrderLogic.report != null
     * @throws NullInputExceptions
     * @throws FormatInputException
     */
    void getClientTop(String noMinOfOrdersString, String minValueOrderString) throws NullInputExceptions, FormatInputException;

    /**
     * @pre pcl != null
     * @pre support != null
     */
    void addPropertyChangeListener(PropertyChangeListener pcl);

    /**
     * @pre OrderLogic.orderListHashMap != null
     * @post result != null
     */
    List<MenuItemDTO> getBuyList(OrderDTO selectedOrder);
}
