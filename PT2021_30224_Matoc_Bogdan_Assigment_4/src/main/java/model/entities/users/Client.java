package model.entities.users;

import model.dto.users.ClientDTO;
import java.io.Serializable;

/**
 * it is used to be able to manipulate and store the data resulting from the database query
 * corresponds to the client table
 */
public class Client extends User implements Serializable {
    public Client() {
        super();
    }

    public Client(String name, String email, String password, String address, String birthdayDate) {
        super(name, email, password, address, birthdayDate);
    }

    public Client(ClientDTO client){
        this(
                //aici tre completat
        );
    }
}
