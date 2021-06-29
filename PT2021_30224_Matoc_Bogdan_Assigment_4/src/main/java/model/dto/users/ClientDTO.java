package model.dto.users;

import model.entities.users.Client;

import java.io.Serializable;

/**
 * it is used to be able to manipulate and store the data resulting from the database query
 * corresponds to the client table
 */
public class ClientDTO extends UserDto {
    public ClientDTO() {
        super();
    }

    public ClientDTO(String name, String email, String password, String address, String birthdayDate) {
        super(name, email, password, address, birthdayDate);
    }

    public ClientDTO(Client client) {
        this(
                client.getName(),
                client.getEmail(),
                client.getPassword(),
                client.getAddress(),
                client.getBirthdayDate()
        );
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name=" + getName() +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                ", birthdayDate=" + getBirthdayDate() +
                "}";
    }
}
