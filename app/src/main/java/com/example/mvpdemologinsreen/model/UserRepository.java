package com.example.mvpdemologinsreen.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class UserRepository implements IUserInterface {
    public static final String TAG = UserRepository.class.getSimpleName();

    private UserDatabase mUserDatabase;
    private ArrayList<User> mListUser;

    public UserRepository(Context context) {
        mUserDatabase = UserDatabase.getInstance(context);
        mListUser = mUserDatabase.getListUser();
    }

    @Override
    public boolean isValid(User user) {
        if(mUserDatabase!=null){
            ArrayList<String> listEmail = mUserDatabase.getListEmail();
            int index = listEmail.indexOf(user.getEmail());
            if(index<0) return true;
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        if(mUserDatabase!=null){
            mUserDatabase.addUser(user);
        }
    }

    @Override
    public String registerAccount(String email, String password) {
        User user = new User(email,password);
        if(password.length()>6 && isValid(user)){
            mUserDatabase.addUser(user);
            return "Register Successfully";
        }
        return "Password must more than 6 character or Email has been existed";
    }

    @Override
    public String loginControl(String email,String password) {

        for(User x:mListUser){
            if(x.getEmail().equals(email)&&x.getPassword().equals(password)) return "Login successfully";
        }
        return "User or Password incorrect";
    }
}
