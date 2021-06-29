package model.dto.users;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class UserDto {
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private SimpleStringProperty address;
    private SimpleStringProperty birthdayDate;

    public UserDto(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        email = new SimpleStringProperty();
        password = new SimpleStringProperty();
        birthdayDate = new SimpleStringProperty();
    }

    public UserDto(String name, String email, String password, String address, String birthdayDate) {
        this();
        this.name.set(name);
        this.address.set(address);
        this.email.set(email);
        this.password.set(password);
        this.birthdayDate.set(birthdayDate);

    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getBirthdayDate() {
        return birthdayDate.get();
    }

    public SimpleStringProperty birthdayDateProperty() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate.set(birthdayDate);
    }

    @Override
    public int hashCode() {
        return getEmail().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserDto)
            return this.getEmail().equals(((UserDto) obj).getEmail());
        return false;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name=" + getName() +
                ", email=" + getEmail() +
                ", address=" + getAddress() +
                ", birthdayDate=" + getBirthdayDate() +
                '}';
    }
}
