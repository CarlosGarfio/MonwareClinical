package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.monwareclinical.R;
import com.monwareclinical.model.User;
import com.monwareclinical.util.Constants;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


public class SplashScreenActivity extends AppCompatActivity {

    Activity fa;

    final long MILLS = 1_000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        fa = this;

        Constants.getInstance(fa).setUser(new User(
                "https://firebasestorage.googleapis.com/v0/b/easyprint-b31e3.appspot.com/o/photos%2FgwOrD2Oo8zaLtym8Xg6YgCrqIIZ2?alt=media&token=bb142b94-12b2-4db8-b2f2-29c1e9d69d36",
                "takenhiduk",
                "Alma Marcela",
                "Montez Cano",
                "mcalmamarcela@gmail.com"));

        new Handler().postDelayed(this::continueToMain, MILLS);
    }

    void continueToMain() {
        startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}