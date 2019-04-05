package com.example.mvpdemologinsreen.screen.login;

public interface LoginContract {
    interface View {
        void showLoginSuccessDialogue();

        void showLoginFailDialogue(Exception e);

        void showRegisterSuccessDialogue();

        void showRegisterFailDialogue(Exception e);
    }

    interface Presenter {
        void accessLoginAccount(String email, String password);

        void registerNewAccount(String email, String password);
    }
}
