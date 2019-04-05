package com.example.mvpdemologinsreen.screen.login;


import com.example.mvpdemologinsreen.model.User;
import com.example.mvpdemologinsreen.model.source.OnDataLoadingCallback;
import com.example.mvpdemologinsreen.model.source.UserRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private UserRepository mUserRepository;
    private LoginContract.View mLoginView;

    public LoginPresenter(UserRepository userRepository, LoginContract.View loginView) {
        mLoginView = loginView;
        mUserRepository = userRepository;
    }

    @Override
    public void accessLoginAccount(String email, String password) {
        User user = new User(email, password);
        mUserRepository.login(user, new OnDataLoadingCallback<Boolean>() {
            @Override
            public void onDataLoaded(Boolean data) {
                if (data == true) mLoginView.showRegisterSuccessDialogue();
                else mLoginView.showLoginFailDialogue(null);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                mLoginView.showRegisterFailDialogue(e);
            }
        });

    }

    @Override
    public void registerNewAccount(String email, String password) {
        User user = new User(email, password);
        mUserRepository.registerAccount(user, new OnDataLoadingCallback<Boolean>() {
            @Override
            public void onDataLoaded(Boolean data) {
                if (data == true) mLoginView.showLoginSuccessDialogue();
                else mLoginView.showRegisterFailDialogue(null);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                mLoginView.showLoginFailDialogue(e);
            }
        });

    }

}
