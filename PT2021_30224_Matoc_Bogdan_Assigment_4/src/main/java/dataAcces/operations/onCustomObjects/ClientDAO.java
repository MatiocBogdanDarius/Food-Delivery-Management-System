package dataAcces.operations.onCustomObjects;

import dataAcces.operations.onGenericObjects.DAO;
import model.entities.users.Client;


/**
 * this class is an instantaneous class that extends the DAO class;
 * at the moment it is empty but in view of a possible extension of the project it can be populated with methods
 * useful for accessing the client table in the database
 */
public class ClientDAO extends DAO<Client> {
    public ClientDAO() {
        super("src/main/resources/client.txt");
    }
}
