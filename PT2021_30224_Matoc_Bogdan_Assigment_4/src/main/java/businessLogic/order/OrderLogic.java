package businessLogic.order;

import businessLogic.BusinessLogic;
import businessLogic.iDeliveryServiceProcessing.IOrderProcessing;
import businessLogic.user.UserLogic;
import dataAcces.operations.onCustomObjects.OrderDAO;
import exceptions.FormatInputException;
import exceptions.IncorrectStartEndHourException;
import exceptions.NullInputExceptions;
import model.dto.menu.MenuDTO;
import model.dto.menu.ProductDTO;
import model.dto.order.OrderDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.users.ClientDTO;
import model.entities.menu.MenuItem;
import model.entities.order.Order;
import model.entities.users.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class contains the logic part on an order
 */
public class OrderLogic implements BusinessLogic<OrderDTO>, IOrderProcessing {
    private static HashMap<OrderDTO, List<MenuItemDTO>> orderListHashMap;
    private static List<OrderDTO> orderDTOList;
    public static String report;
    private PropertyChangeSupport support;
    private OrderDAO orderDAO;
    private Order newOrder;

    public OrderLogic() {
        orderDAO = new OrderDAO();
        this.support = new PropertyChangeSupport(this);
        findAll();
    }

    @Override
    public List<OrderDTO> findAll() {
        if (orderDTOList != null)
            return orderDTOList;
        if (orderListHashMap == null) {
            HashMap<Order, List<MenuItem>> orders = orderDAO.findAll();
            orderListHashMap = new HashMap<>();
            orders.forEach((order, buyList) -> orderListHashMap.put(
                    new OrderDTO(order), MenuItemDTO.convertListMenuItemToListOfMenuItemDTO(buyList)));
        }
        orderDTOList = new ArrayList<>(orderListHashMap.keySet());
        return orderDTOList;
    }

    @Override
    public OrderDTO findLast() {
        return orderDTOList.stream().max(OrderDTO::compareTo).get();
    }

    @Override
    public void placeOrder(List<MenuItemDTO> buyList) throws NullInputExceptions {
        if (buyList == null || buyList.isEmpty())
            throw new NullInputExceptions("The order must contain one or more products/menus!");
        Order newOrderTemporaryObject = new Order(buyList);
        support.firePropertyChange("newOrder", this.newOrder, newOrderTemporaryObject);
        newOrder = newOrderTemporaryObject;
        add(new OrderDTO(newOrder), buyList);
    }

    @Override
    public void getOrdersFromInterval(LocalDate date, Integer startHour, Integer endHour) throws NullInputExceptions, IncorrectStartEndHourException {
        if (date == null || startHour == null || endHour == null)
            throw new NullInputExceptions("Before pressing the \"Get Orders From Interval\" button you must select " +
                    "the date, start time and end time of the time interval for which you want the report!");
        if (endHour < startHour)
            throw new IncorrectStartEndHourException("The end time must be greater than or equal to the start time!");

        List<OrderDTO> filteredOrdersList = orderDTOList.stream()
                .filter(order -> LocalDateTime.parse(order.getDate()).toLocalDate().equals(date))
                .filter(order -> LocalDateTime.parse(order.getDate()).getHour() >= startHour)
                .filter(order -> LocalDateTime.parse(order.getDate()).getHour() <= endHour)
                .collect(Collectors.toList());
        if (filteredOrdersList.isEmpty())
            report = "No order was placed within the specified range";
        else {
            report = "";
            filteredOrdersList.forEach(orderDTO -> {
                report += orderDTO.toString() + "\n\tBUY LIST:\n";
                orderListHashMap.get(orderDTO).forEach(menuItemDTO -> report += "\t\t" + menuItemDTO.toString() + "\n");
            });
            report += "\n\n";
        }
        System.out.println(report);
    }

    @Override
    public void getTopProduct(String noMinOfPurchasesOfProductString) throws NullInputExceptions, FormatInputException {
        if (noMinOfPurchasesOfProductString == null)
            throw new NullInputExceptions("Before pressing the \"Get Top Products\" button you must write " +
                    " the minimum number of orders for which you want the report!");
        try {
            int noMinOfPurchasesOfProduct = Integer.parseInt(noMinOfPurchasesOfProductString);
            HashMap<MenuItemDTO, Integer> productsWithNoOfRepetitions = getMapOfProductsWithNoOfRepetitions(orderDTOList);
            productsWithNoOfRepetitions = reduceProductWithALowerAppearanceThan(noMinOfPurchasesOfProduct, productsWithNoOfRepetitions);
            getReportStringFrom(productsWithNoOfRepetitions);
        } catch (NumberFormatException e) {
            throw new FormatInputException("The minimum number must be an integer!");
        }

    }

    @Override
    public void getDayReport(LocalDate date, String noMinOfPurchasesOfProductString) throws NullInputExceptions, FormatInputException {
        if (date == null || noMinOfPurchasesOfProductString == null)
            throw new NullInputExceptions("Before pressing the \"Get Day Report\" button you must select " +
                    "the date and write the minimum number of orders for which you want the report!");

        try {
            int noMinOfPurchasesOfProduct = Integer.parseInt(noMinOfPurchasesOfProductString);
            List<OrderDTO> filteredOrdersListByDate = orderDTOList.stream()
                    .filter(order -> LocalDateTime.parse(order.getDate()).toLocalDate().equals(date))
                    .collect(Collectors.toList());
            HashMap<MenuItemDTO, Integer> productsWithNoOfRepetitions = getMapOfProductsWithNoOfRepetitions(filteredOrdersListByDate);
            productsWithNoOfRepetitions = reduceProductWithALowerAppearanceThan(noMinOfPurchasesOfProduct, productsWithNoOfRepetitions);
            getReportStringFrom(productsWithNoOfRepetitions);

        } catch (NumberFormatException e) {
            throw new FormatInputException("The minimum number must be an integer!");
        }
    }

    @Override
    public void getClientTop(String noMinOfOrdersString, String minValueOrderString) throws NullInputExceptions, FormatInputException {
        if (noMinOfOrdersString == null || minValueOrderString == null)
            throw new NullInputExceptions("Before pressing the \"Get Client Top\" button you must write " +
                    " the minimum number of orders and the minimum value of the order for which you want the report!");

        try {
            int noMinOfOrders = Integer.parseInt(noMinOfOrdersString);
            int minValueOrder = Integer.parseInt(minValueOrderString);

            List<OrderDTO> orderWithPriceMoreThanMinValue = orderDTOList.stream()
                    .filter(order -> order.getPrice() >= minValueOrder)
                    .collect(Collectors.toList());
            HashMap<ClientDTO, Integer> clientsWithNoOfOrders = getMapOfClientsWithNoOfOrders(orderWithPriceMoreThanMinValue);
            clientsWithNoOfOrders = reduceClientWithALowerNoOrdersThan(noMinOfOrders, clientsWithNoOfOrders);
            getReportStringFromClientsMap(clientsWithNoOfOrders);

        } catch (NumberFormatException e) {
            throw new FormatInputException("The minimum number and the minimum value must be an integer!");
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    @Override
    public List<MenuItemDTO> getBuyList(OrderDTO selectedOrder) {
        if (selectedOrder == null)
            return new ArrayList<>();
        List<MenuItemDTO> buyList = orderListHashMap.get(selectedOrder);
        if (buyList.isEmpty())
            return new ArrayList<>();
        return buyList;
    }

    private void add(OrderDTO order, List<MenuItemDTO> buyList) {
        orderListHashMap.put(order, buyList);
        orderDTOList.add(order);
        new Thread(() -> orderDAO.add(newOrder, MenuItem.convertListMenuItemDTOToListOfMenuItem(buyList)));
    }

    private HashMap<MenuItemDTO, Integer> getMapOfProductsWithNoOfRepetitions(List<OrderDTO> orders) {
        HashMap<MenuItemDTO, Integer> productsWithNoOfRepetitions = new HashMap<>();
        orders.forEach(orderDTO ->
                orderListHashMap.get(orderDTO).forEach(menuItemDTO ->
                        getMapOfProductsWithNoOfRepetitionsForOneMenuItem(menuItemDTO, productsWithNoOfRepetitions)));
        return productsWithNoOfRepetitions;
    }

    private void getMapOfProductsWithNoOfRepetitionsForOneMenuItem(MenuItemDTO menuItemDTO, HashMap<MenuItemDTO, Integer> productsWithNoOfRepetitions) {
        if(menuItemDTO instanceof ProductDTO){
            addOrIncrementInMap(menuItemDTO, productsWithNoOfRepetitions);
        }else{
            MenuDTO menuDTO = (MenuDTO) menuItemDTO;
            menuDTO.getMenuItemsDto().forEach(menuItemDTO1 ->
                    getMapOfProductsWithNoOfRepetitionsForOneMenuItem(menuItemDTO1, productsWithNoOfRepetitions));
        }
    }

    private void addOrIncrementInMap(MenuItemDTO key, HashMap<MenuItemDTO, Integer> map) {
        if (map.containsKey(key)) {
            int oldNoOfRepetitions = map.get(key);
            map.put(key, ++oldNoOfRepetitions);
        } else {
            map.put(key, 1);
        }
    }

    private void addOrIncrementInMap(ClientDTO key, HashMap<ClientDTO, Integer> map) {
        if (map.containsKey(key)) {
            int oldNoOfRepetitions = map.get(key);
            map.put(key, ++oldNoOfRepetitions);
        } else {
            map.put(key, 1);
        }
    }

    private HashMap<MenuItemDTO, Integer> reduceProductWithALowerAppearanceThan(int valueToCompare, HashMap<MenuItemDTO, Integer> map){
        HashMap<MenuItemDTO, Integer> filteredMap = new HashMap<>();
        map.forEach((product, noOfRepetitions) -> {
            if (noOfRepetitions >= valueToCompare){
                filteredMap.put(product, noOfRepetitions);
            }
        });
        return filteredMap;
    }

    private void getReportStringFrom(HashMap<MenuItemDTO, Integer> map) {
        if (map.isEmpty())
            report =  "No products were found that met the requirements";
        else {
            List<MenuItemDTO> products =
                    map.keySet().stream().sorted(Comparator.comparingInt(map::get)).collect(Collectors.toList());
            report = "Products: \n\n";
            products.forEach(menuItemDTO -> report += "\t" + menuItemDTO.toString()
                    + "\n\tNumber of orders: " + map.get(menuItemDTO) + "\n\n");
        }
    }

    private HashMap<ClientDTO, Integer> getMapOfClientsWithNoOfOrders(List<OrderDTO> orders) {
        HashMap<ClientDTO, Integer> clientsWithNoOfOrders = new HashMap<>();
        orders.forEach(orderDTO -> addOrIncrementInMap(orderDTO.getClient(), clientsWithNoOfOrders));
        return clientsWithNoOfOrders;
    }

    private HashMap<ClientDTO, Integer> reduceClientWithALowerNoOrdersThan(int valueToCompare, HashMap<ClientDTO, Integer> map) {
        HashMap<ClientDTO, Integer> filteredMap = new HashMap<>();
        map.forEach((client, noOfRepetitions) -> {
            if (noOfRepetitions >= valueToCompare){
                filteredMap.put(client, noOfRepetitions);
            }
        });
        return filteredMap;
    }

    private void getReportStringFromClientsMap(HashMap<ClientDTO, Integer> map) {
        if (map.isEmpty())
            report =  "No clients were found that met the requirements";
        else {
            List<ClientDTO> products =
                    map.keySet().stream().sorted(Comparator.comparingInt(map::get)).collect(Collectors.toList());
            report = "Client: \n\n";
            products.forEach(menuItemDTO -> report += "\t" + menuItemDTO.toString()
                    + "\n\tNumber of orders: " + map.get(menuItemDTO) + "\n\n");
        }
    }
}
