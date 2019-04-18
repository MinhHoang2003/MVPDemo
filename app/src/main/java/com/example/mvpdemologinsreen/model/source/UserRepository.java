package com.example.mvpdemologinsreen.model.source;

import android.content.Context;

import com.example.mvpdemologinsreen.model.User;
import com.example.mvpdemologinsreen.model.source.local.LocalDataSource;

import java.util.List;

public class UserRepository implements UserDataSource {

    private static UserRepository sUserRepository;
    private UserDataSource mLocalDataSource;
    private List<User> mCachedUsers;

    public UserRepository(UserDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static UserRepository getInstance(Context context) {
        if (sUserRepository == null) {
            sUserRepository = new UserRepository(LocalDataSource.getInstance(context));
        }
        return sUserRepository;
    }

    @Override
    public boolean addUser(User user) {
        return mLocalDataSource.addUser(user);
    }

    @Override
    public void getUsers(OnDataLoadingCallback callback) {
        mLocalDataSource.getUsers(new OnDataLoadingCallback<List<User>>() {
            @Override
            public void onDataLoaded(List<User> data) {
                mCachedUsers = data;
            }

            @Override
            public void onDataNotAvailable(Exception e) {

            }
        });
    }

    @Override
    public void registerAccount(User user, OnDataLoadingCallback<Boolean> callback) {
        mLocalDataSource.registerAccount(user, callback);
    }

    @Override
    public void login(User user, OnDataLoadingCallback<Boolean> callback) {
        mLocalDataSource.login(user, callback);
    }

}
