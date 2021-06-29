package dataAcces.operations.onCustomObjects;

import dataAcces.operations.onGenericObjects.DAO;
import model.entities.users.Admin;

public class AdminDAO extends DAO<Admin> {
    public AdminDAO() {
        super("src/main/resources/admin.txt");
    }
}
