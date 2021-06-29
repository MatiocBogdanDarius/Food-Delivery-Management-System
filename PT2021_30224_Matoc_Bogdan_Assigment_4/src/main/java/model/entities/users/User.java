package model.entities.users;

import java.io.Serializable;


public abstract class User implements Serializable {
    private String name;
    private String email;
    private String address;
    private String password;
    private String birthdayDate;

    public User(){
        name = "";
        address = "";
        email = "";
        password = "";
        birthdayDate = "";
    }

    public User( String name, String email, String password, String address, String birthdayDate) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.birthdayDate = birthdayDate;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

}
