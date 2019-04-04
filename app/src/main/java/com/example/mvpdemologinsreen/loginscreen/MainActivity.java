package com.example.mvpdemologinsreen.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpdemologinsreen.R;

public class MainActivity extends AppCompatActivity implements ILoginContract.ILoginView, View.OnClickListener {

    private ILoginContract.ILoginPresenter mILoginPresenter;
    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButtonLogin, mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);

        mILoginPresenter = new LoginPresenter(this);

    }

    @Override
    public void systemResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initView(){
        mEditTextEmail = findViewById(R.id.edtEmail);
        mEditTextPassword = findViewById(R.id.edtPassword);
        mButtonLogin = findViewById(R.id.btnLogin);
        mButtonRegister = findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View v) {
        String email = mEditTextEmail.getText().toString();
        String password =  mEditTextPassword.getText().toString();
        switch (v.getId()){
            case R.id.btnLogin:
                mILoginPresenter.loginAccess(email,password);
                break;
            case R.id.btnRegister:
                mILoginPresenter.register(email,password);
        }
    }
}
