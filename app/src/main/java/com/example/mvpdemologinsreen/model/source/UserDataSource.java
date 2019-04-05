package com.example.mvpdemologinsreen.model.source;

import com.example.mvpdemologinsreen.model.User;

import java.util.List;

public interface UserDataSource {
    boolean addUser(User user);

    void getUsers(OnDataLoadingCallback<List<User>> callback);

    void registerAccount(User user, OnDataLoadingCallback<Boolean> callback);

    void login(User user, OnDataLoadingCallback<Boolean> callback);
}
