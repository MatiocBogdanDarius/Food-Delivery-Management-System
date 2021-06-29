package model.entities.users;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin() {
    }

    public Admin(String name, String email, String password, String address, String birthdayDate) {
        super(name, email, password, address, birthdayDate);
    }

}
