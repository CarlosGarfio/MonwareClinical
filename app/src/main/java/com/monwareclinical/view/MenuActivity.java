package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monwareclinical.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity {

    Activity fa;

    BottomNavigationView bottomNavigationView;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fa = this;
        showToolbar();
        setUpBottomNav();
    }

    void showToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView imgLogo = findViewById(R.id.toolbarLogo);
        txtTitle = findViewById(R.id.toolbarTitle);
        CircleImageView imgProfile = findViewById(R.id.toolbarProfile);

        setSupportActionBar(toolbar);

        imgLogo.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        txtTitle.setText("Menú");
        imgProfile.setImageDrawable(getDrawable(R.drawable.forgotten_password));

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        txtTitle.setText(getString(R.string.menu_home));
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    txtTitle.setText(getString(R.string.menu_home));
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_search:
                    txtTitle.setText(getString(R.string.menu_search));
                    fragment = new SearchFragment();
                    break;
                    case R.id.navigation_settings:
                    txtTitle.setText(getString(R.string.menu_settings));
                    fragment = new SearchFragment();
                    break;
            }

            return loadFragment(fragment);
        });
    }

    boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}