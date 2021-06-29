package model.entities.users;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    public Employee() {
    }

    public Employee(String name, String email, String password, String address, String birthdayDate) {
        super(name, email, password, address, birthdayDate);
    }
}
