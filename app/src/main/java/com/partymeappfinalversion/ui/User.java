package com.partymeappfinalversion.ui;

/**
 * Created by franciscoadelandasil on 10/05/2016.
 */
public class User {
    //vareable to store users detail
    String name, email, password, repassword;
    //first constructor to be used to register a user
    public  User (String name, String email, String password, String repassword){
        this.name = name;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }
    public  User (String name, String password){
        this.name = name;
        this.email = "";
        this.password = password;
        this.repassword = "";
    }
}
