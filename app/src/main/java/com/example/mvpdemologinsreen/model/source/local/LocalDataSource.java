package com.example.mvpdemologinsreen.model.source.local;

import android.content.Context;

import com.example.mvpdemologinsreen.model.User;
import com.example.mvpdemologinsreen.model.source.OnDataLoadingCallback;
import com.example.mvpdemologinsreen.model.source.UserDataSource;

import java.sql.SQLException;
import java.util.List;

public class LocalDataSource implements UserDataSource {

    private static LocalDataSource sLocalDataSource;
    private UserDAO mUserDAO;

    public LocalDataSource(UserDAO userDatabase) {
        mUserDAO = userDatabase;
    }

    public static LocalDataSource getInstance(Context context) {
        if (sLocalDataSource == null) {
            sLocalDataSource = new LocalDataSource(UserDatabase.getInstance(context));
        }
        return sLocalDataSource;
    }

    @Override
    public boolean addUser(User user) {
        return mUserDAO.addUser(user);
    }

    @Override
    public void getUsers(OnDataLoadingCallback callback) {
        LocalUserAsyncTask<List<User>> listLocalUserAsyncTask = new LocalUserAsyncTask<>(new GetDataHandler<List<User>>() {
            @Override
            public List<User> getData() throws SQLException {
                return mUserDAO.getUsers();
            }
        }, callback);
        listLocalUserAsyncTask.execute();
    }

    @Override
    public void registerAccount(User user, OnDataLoadingCallback<Boolean> callback) {
        try {
            if (!mUserDAO.hasEmail(user) && mUserDAO.addUser(user)) callback.onDataLoaded(true);
            else callback.onDataLoaded(false);
        } catch (SQLException e) {
            callback.onDataNotAvailable(e);
        }

    }

    @Override
    public void login(User user, OnDataLoadingCallback<Boolean> callback) {
        try {
            if (mUserDAO.isValidUser(user)) callback.onDataLoaded(true);
            else callback.onDataLoaded(false);
        } catch (SQLException e) {
            callback.onDataNotAvailable(e);
        }
    }
}
