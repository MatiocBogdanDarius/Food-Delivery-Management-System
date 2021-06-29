package model.entities.order;


import businessLogic.user.UserLogic;
import model.dto.menu.MenuItemDTO;
import model.entities.users.Client;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * it is used to be able to manipulate and store the data resulting from the database query
 * corresponds to the _order table
 */
public class Order implements Serializable {
    private LocalDateTime dateTime;
    private int price;
    private Client client;

    public Order(LocalDateTime dateTime, int price, Client client) {
        this.dateTime = dateTime;
        this.price = price;
        this.client = client;

    }

    public Order(List<MenuItemDTO> buyList) {
        dateTime = LocalDateTime.now();
        client = (Client) UserLogic.currentUser;
        setPrice(buyList);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private void setPrice(List<MenuItemDTO> buyListDTO) {
        AtomicInteger atomicIntegerPrice = new AtomicInteger(0);
        buyListDTO.forEach(buyListDTOItem -> atomicIntegerPrice.addAndGet(buyListDTOItem.getPrice()));
        price = atomicIntegerPrice.get();
    }
}
