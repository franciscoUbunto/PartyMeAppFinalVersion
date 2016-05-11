package com.partymeappfinalversion.ui;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by franciscoadelandasil on 10/05/2016.
 */
public class UserInLocalDb {
    //file to store user names
    public  static final String sNames = "UserDetails";
    //used to store in the local phone.
    SharedPreferences userSharedlDb;

    //constructor to allow instatiate the Shared DB
    public UserInLocalDb(Context context){
        // create an instace of the user on local DB passaing the file where the user details is stored as reference
        userSharedlDb = context.getSharedPreferences(sNames, 0);
    }
    //method to store use data
    public void userDataStore(User user){
        SharedPreferences.Editor sEditor = userSharedlDb.edit();
        sEditor.putString("name", user.name);
        sEditor.putString("email", user.email);
        sEditor.putString("password", user.password);
        sEditor.putString("confPasword", user.repassword );
        sEditor.commit();
    }

    //method to return a user that has logged in
    public User getLoggedUser(){
        String name = userSharedlDb.getString("name", "");
        String email = userSharedlDb.getString("email", "");
        String password = userSharedlDb.getString("password", "");
        String rePassword = userSharedlDb.getString("confPaswword", "");

        User userInStore = new User(name, email, password, rePassword);
        return userInStore;

    }
    //method to set the users logged in
    public void setLoggedUser(boolean loggedIn){
        SharedPreferences.Editor sEditor = userSharedlDb.edit();
        sEditor.putBoolean("loggedIn", loggedIn);
        sEditor.commit();
    }

    //method to get users logged in
    public boolean userLogged(){
        if(userSharedlDb.getBoolean("loggedIn", false) == true){
            return true;
        }
        else{
            return false;
        }
    }

    //method to clear user data after logout
    public void clearDataFromUser(){
        SharedPreferences.Editor sEditor = userSharedlDb.edit();
        sEditor.clear();
        sEditor.commit();
    }

}
