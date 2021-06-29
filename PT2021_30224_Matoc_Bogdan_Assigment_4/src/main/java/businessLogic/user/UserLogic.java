package businessLogic.user;

import dataAcces.operations.onCustomObjects.AdminDAO;
import dataAcces.operations.onCustomObjects.ClientDAO;
import dataAcces.operations.onCustomObjects.EmployeeDAO;
import model.dto.users.AdminDTO;
import model.entities.users.Admin;
import model.entities.users.Client;
import model.entities.users.Employee;
import model.entities.users.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class UserLogic {
    private static List<Client> clients;
    private static List<Admin> admins;
    private static List<Employee> employees;
    public static User currentUser;

    public UserLogic() {
        admins = (new AdminDAO()).findAll();
        clients = (new ClientDAO()).findAll();
        employees = (new EmployeeDAO()).findAll();
    }

    public boolean isDisposableEmail(String email) {
        if (getAdminByEmail(email) == null && getEmployeeByEmail(email) == null && getClientByEmail(email) == null){
            return true;
        }
        return false;
    }

    public Admin getAdminByEmailAndPassword(String email, String password) {
        Admin possibleAdmin = getAdminByEmail(email);
        if(possibleAdmin != null && possibleAdmin.getPassword().equals(password))
            return possibleAdmin;
        return null;
    }

    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        Employee possibleEmployee = getEmployeeByEmail(email);
        if(possibleEmployee != null && possibleEmployee.getPassword().equals(password))
            return possibleEmployee;
        return null;
    }

    public Client getClientByEmailAndPassword(String email, String password) {
        Client possibleClient = getClientByEmail(email);
        if(possibleClient != null && possibleClient.getPassword().equals(password))
            return possibleClient;
        return null;    }

    private Admin getAdminByEmail(String email) {
        List<Admin> adminsWithEmail =
                admins.stream().filter(admin -> admin.getEmail().equals(email)).collect(Collectors.toList());
        if(adminsWithEmail.isEmpty())
            return null;
        return adminsWithEmail.get(0);
    }

    private Employee getEmployeeByEmail(String email) {
        List<Employee> employeesWithEmail =
                employees.stream().filter(admin -> admin.getEmail().equals(email)).collect(Collectors.toList());
        if(employeesWithEmail.isEmpty())
            return null;
        return employeesWithEmail.get(0);
    }

    private Client getClientByEmail(String email) {
        List<Client> clientsWithEmail =
                clients.stream().filter(admin -> admin.getEmail().equals(email)).collect(Collectors.toList());
        if(clientsWithEmail.isEmpty())
            return null;
        return clientsWithEmail.get(0);
    }
}
