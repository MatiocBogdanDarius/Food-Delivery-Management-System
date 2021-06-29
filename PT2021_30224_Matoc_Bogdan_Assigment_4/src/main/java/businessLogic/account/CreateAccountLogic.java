package businessLogic.account;

import businessLogic.user.UserLogic;
import dataAcces.operations.onCustomObjects.ClientDAO;
import exceptions.IncompatiblePasswordsException;
import exceptions.InvalidBirthdayDateExceptions;
import exceptions.NullInputExceptions;
import exceptions.UnavailableEmailException;
import model.entities.users.Client;

import java.time.LocalDate;
import java.time.Period;

public class CreateAccountLogic {

    public void createAccount(String name, String email, String password, String confirmPassword, String address, LocalDate birthdayDate)
            throws UnavailableEmailException, IncompatiblePasswordsException, InvalidBirthdayDateExceptions, NullInputExceptions {
        chekIfIsNotNullInputs(name, email, password, confirmPassword, address, birthdayDate);
        checkAvailabilityEmail(email);
        checkCompatiblyPasswords(password, confirmPassword);
        checkValidityBirthdayDate(birthdayDate);
        saveNewAccount(new Client(name, email, password, address, birthdayDate.toString()));
    }

    private void saveNewAccount(Client client) {
        UserLogic.currentUser = client;
        (new ClientDAO()).add(client);
    }

    private void chekIfIsNotNullInputs(String name, String email, String password, String confirmPassword, String address, LocalDate birthdayDate)
            throws NullInputExceptions {
        if(name == null || email == null || password == null || confirmPassword == null || address == null || birthdayDate == null){
            throw new NullInputExceptions("All fields are required!");
        }
    }

    private void checkValidityBirthdayDate(LocalDate birthdayDate) throws InvalidBirthdayDateExceptions {
        if(Period.between(birthdayDate, LocalDate.now()).getYears() < 14){
            throw new InvalidBirthdayDateExceptions("You had to be at least 14 years old to create an account");
        }
    }

    private void checkAvailabilityEmail(String email) throws UnavailableEmailException {
        if(!(new UserLogic()).isDisposableEmail(email)){
            throw new UnavailableEmailException("There is already an account associated with this email address");
        }
    }

    private void checkCompatiblyPasswords(String password, String confirmPassword) throws IncompatiblePasswordsException {
        if(!password.equals(confirmPassword)) {
            throw new IncompatiblePasswordsException("The two passwords are not identical. They must be identical!");
        }
    }

    
}
