package Start;

import businessLogic.user.UserLogic;
import businessLogic.menu.MenuLogic;
import businessLogic.menu.ProductLogic;
import businessLogic.order.OrderLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;



public class Start extends Application {
    public static void main(String[] args) {
            Application.launch(args);
//        sterge();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLists();
        URL url = new File("src/main/java/presentationLayer/view/account/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Store");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void initLists() {
        new UserLogic();
        new ProductLogic();
        new MenuLogic();
        new OrderLogic();
    }

    public static void sterge(){
//        (new AdminDAO()).add(
//                new Admin("Matioc Bogdan", "email@yahoo.com", "parola", "Bocsa", "1999-12-2")
//        );
//
//        (new AdminDAO()).findAll().forEach(admin ->
//                System.out.println(admin.getEmail() + "\t" + admin.getName()));
//
//        (new EmployeeDAO()).add(
//                new Employee("Jula Vlad", "vlad@yahoo.com", "parola", "Bocsa", "2000-03-02")
//        );

//        (new ClientDAO()).add(
//                new Client("Marco Tei", "marco@yahoo.com", "parola", "Bocsa", "2000-03-02")
//        );
//        Client client = (Client) UserLogic.currentUser;
//        System.out.println(client.getName() + "\n" + client.getAddress() + "\n" +client.getEmail() + "\n");

    }
}
