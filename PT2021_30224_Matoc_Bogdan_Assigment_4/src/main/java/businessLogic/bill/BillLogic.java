package businessLogic.bill;

import businessLogic.order.OrderLogic;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import model.dto.order.OrderDTO;
import model.dto.menu.MenuItemDTO;
import model.dto.users.ClientDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * this class contains the logic part of an invoice
 */
public class BillLogic {
    public static String billString;
    private OrderLogic orderLogic;

    public BillLogic() {
        this.orderLogic = new OrderLogic();
    }

    public String createBill(){
        StringBuilder bill = new StringBuilder();
        OrderDTO lastOrder = orderLogic.findLast();
        bill.append("BILL \n")
                .append("DATE ORDER: ")
                .append(lastOrder.getDate())
                .append("\n\n")
                .append("CLIENT: ")
                .append(getClientBillStringFormat(lastOrder.getClient()))
                .append("\n\n")
                .append(createBuyListBillFormat(orderLogic.getBuyList(lastOrder)));

        billString = bill.toString();
        return billString;
    }

    private String getClientBillStringFormat(ClientDTO client) {
         return  "\nNAME: " +
                client.getName() +
                "\nADDRESS: " +
                client.getAddress() +
                "\nEMAIL: " +
                client.getEmail();
    }

    private String createBuyListBillFormat(List<MenuItemDTO> buyList) {
        StringBuilder buyListBillStringFormat = new StringBuilder();
        buyListBillStringFormat.append("NAME PRODUCT")
                .append("\t")
                .append("RATING")
                .append("\t")
                .append("CALORIES")
                .append("\n");

        buyList.forEach(buyListMember ->{
            buyListBillStringFormat.append(buyListMember.toString()).append("\n");
        });
        return buyListBillStringFormat.toString();
    }


}
