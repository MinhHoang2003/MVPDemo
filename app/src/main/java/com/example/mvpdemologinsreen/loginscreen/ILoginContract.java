package com.example.mvpdemologinsreen.loginscreen;

public interface ILoginContract {
    interface ILoginView{
        void systemResult(String message);
    }
    interface ILoginPresenter{
        void loginAccess(String email,String password);
        void register(String email,String password);
    }
}
