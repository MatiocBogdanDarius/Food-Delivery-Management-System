package dataAcces.operations.onCustomObjects;

import dataAcces.operations.onGenericObjects.DAO;
import model.dto.menu.MenuItemDTO;
import model.entities.menu.MenuItem;
import model.entities.order.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * this class is an instantaneous class that extends the DAO class;
 * at the moment it is empty but in view of a possible extension of the project it can be populated with methods
 * useful for accessing the order table in the database
 */
public class OrderDAO {
    private static HashMap<Order, List<MenuItem>> orders;
    private String filename;

    public OrderDAO() {
        filename = "src/main/resources/orders.txt";
    }

    public HashMap<Order, List<MenuItem>> findAll() {
        orders = new HashMap<>();
        FileInputStream file = null;
        ObjectInputStream in = null;
        try {
            file = new FileInputStream(filename);
            if (file.available() != 0) { //file is empty
                in = new ObjectInputStream(file);
                Object fileContent = in.readObject();
                if (fileContent != null) {
                    orders = (HashMap<Order, List<MenuItem>>) fileContent;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (file != null)
                    file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    public void add(Order newOrder, List<MenuItem> buyList) {
        if(orders == null)
            findAll();

        FileOutputStream file = null;
        ObjectOutputStream out = null;
        try {
            file = new FileOutputStream(filename, false);
            out = new ObjectOutputStream(file);
            out.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (file != null)
                    file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
