package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.monwareclinical.R;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


public class SplashScreenActivity extends AppCompatActivity {

    final long MILLS = 1_000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this::continueToMain, MILLS);
    }

    void continueToMain() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}