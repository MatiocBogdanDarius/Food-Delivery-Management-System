package dataAcces.operations.onCustomObjects;

import dataAcces.operations.onGenericObjects.DAO;
import model.entities.users.Employee;

public class EmployeeDAO extends DAO<Employee> {
    public EmployeeDAO() {
        super("src/main/resources/employee.txt");
    }
}
