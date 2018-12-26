package com.example.android.asap.Model;


public class User {
    String phonenumber;
    String username;
    String password;
    public User()
    {

    }

    public User( String username, String password,String phonenumber) {
        //this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
    }
    public User( String username, String password) {
        //this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;

    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
