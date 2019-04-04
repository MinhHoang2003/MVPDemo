package com.example.mvpdemologinsreen.model;

public interface IUserInterface {
    boolean isValid(User user);
    void addUser(User user);
    String registerAccount(String email,String password);
    String loginControl(String email,String password);

}
