package com.example.mvpdemologinsreen.loginscreen;

import android.content.Context;

import com.example.mvpdemologinsreen.model.User;
import com.example.mvpdemologinsreen.model.UserRepository;

public class LoginPresenter implements ILoginContract.ILoginPresenter {

    private UserRepository mUserRepository;
    private ILoginContract.ILoginView mLoginView;

    public LoginPresenter(ILoginContract.ILoginView loginView) {
        mLoginView = loginView;
        mUserRepository = new UserRepository((Context) mLoginView);
    }

    @Override
    public void loginAccess(String email, String password) {
       String message =  mUserRepository.loginControl(new User(email,password));
       mLoginView.systemResult(message);
    }

    @Override
    public void register(String email, String password) {
        String message = mUserRepository.registerAccount(email,password);
        mLoginView.systemResult(message);
    }
}
