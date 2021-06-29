package model.dto.order;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.dto.DTO;
import model.dto.menu.MenuDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.menu.ProductDTO;
import model.dto.users.ClientDTO;
import model.entities.order.Order;
import model.entities.menu.Menu;
import model.entities.menu.MenuItem;
import model.entities.menu.Product;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to transfer a order's data between the presentation layer and the business Logic layer
 */
public class OrderDTO implements Comparable<OrderDTO>, DTO {
    private SimpleStringProperty date;
    private SimpleIntegerProperty price;
    private SimpleStringProperty clientName;
    private ClientDTO client;

    public OrderDTO() {
        date = new SimpleStringProperty();
        price = new SimpleIntegerProperty();
        client = new ClientDTO();
        clientName = new SimpleStringProperty();
    }

    public OrderDTO(LocalDate date, int price, @NotNull ClientDTO client, List<MenuItemDTO> buyList) {
        this();
        this.date.set(date.toString());
        this.price.set(price);
        this.clientName.set(client.getName());
        this.client = client;
    }

    public OrderDTO(Order order){
        this();
        this.date.set(order.getDateTime().toString());
        this.price.set(order.getPrice());
        this.clientName.set(order.getClient().getName());
        this.client = new ClientDTO(order.getClient());
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public String getClientName() {
        return clientName.get();
    }

    public void setClientName(String clientName) {
        this.clientName.set(clientName);
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
        clientName.set(client.getName());
    }

    @Override
    public int hashCode() {
        return getDate().hashCode();
    }

    @Override
    public String toString() {
        return  "ORDER:\n\tDATE: " + getDate() + "\n\tCLIENT: " + getClient().toString() + "\n\tPRICE:" + getPrice();
    }

    @Override
    public int compareTo(@NotNull OrderDTO order) {
        return this.getDate().compareTo(order.getDate());
    }
}
