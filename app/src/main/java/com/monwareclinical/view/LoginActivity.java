package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.monwareclinical.R;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {

    Activity fa;

    Button btnLogin;
    Button btnSignUpMe;
    TextView btnForgottenPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComps();
        initActions();
        initStuff();
    }

    void initComps() {
        fa = this;
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUpMe = findViewById(R.id.btnSignUpMe);
        btnForgottenPassword = findViewById(R.id.forgottenPassword);
    }

    void initActions() {
        btnLogin.setOnClickListener(this);
        btnSignUpMe.setOnClickListener(this);
        btnForgottenPassword.setOnClickListener(this);
    }

    void initStuff() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(fa, MenuActivity.class));
                finish();
                break;
            case R.id.btnSignUpMe:
                startActivity(new Intent(fa, SignUpActivity.class));
                break;
            case R.id.forgottenPassword:
                startActivity(new Intent(fa, ForgottenPasswordActivity.class));
                break;
        }
    }
}