package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.monwareclinical.R;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnLogin;
    Button btnSignUpMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComps();
        initActions();
        initStuff();
    }

    void initComps() {
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUpMe = findViewById(R.id.btnSignUpMe);
    }

    void initActions() {
        btnLogin.setOnClickListener(this);
        btnSignUpMe.setOnClickListener(this);
    }

    void initStuff() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                break;
            case R.id.btnSignUpMe:
                break;
        }
    }
}