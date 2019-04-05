package com.example.mvpdemologinsreen.screen.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpdemologinsreen.R;
import com.example.mvpdemologinsreen.model.source.UserRepository;

public class MainActivity extends AppCompatActivity implements LoginContract.View, android.view.View.OnClickListener {

    private LoginContract.Presenter mPresenter;
    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButtonLogin, mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);

        mPresenter = new LoginPresenter(UserRepository.getInstance(this), this);
    }

    @Override
    public void showLoginSuccessDialogue() {
        Toast.makeText(this, R.string.MSG_LOGIN_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailDialogue(Exception e) {
        if (e == null) Toast.makeText(this, R.string.MSG_LOGIN_ERROR, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterSuccessDialogue() {
        Toast.makeText(this, R.string.MSG_REGISTER_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterFailDialogue(Exception e) {
        if (e == null)
            Toast.makeText(this, R.string.MSG_ERROR_ADDING_USER, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(android.view.View v) {
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        switch (v.getId()) {
            case R.id.btnLogin:
                mPresenter.accessLoginAccount(email, password);
                break;
            case R.id.btnRegister:
                mPresenter.registerNewAccount(email, password);
        }
    }

    private void initView() {
        mEditTextEmail = findViewById(R.id.edtEmail);
        mEditTextPassword = findViewById(R.id.edtPassword);
        mButtonLogin = findViewById(R.id.btnLogin);
        mButtonRegister = findViewById(R.id.btnRegister);
    }
}
