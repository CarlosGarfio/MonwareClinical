package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.monwareclinical.R;
import com.monwareclinical.util.SetUpToolBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileActivity extends AppCompatActivity implements
        View.OnClickListener {

    Activity fa;

    SetUpToolBar toolBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    CircleImageView imgProfile;
    TextView txtName;
    TextView txtUsername;
    TextView txtEmail;
    Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComps();
        initActions();
        initStuff();
        showToolbar();
    }

    void initComps() {
        fa = this;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        imgProfile = findViewById(R.id.imgProfile);
        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);

        btnEditProfile = findViewById(R.id.btnEditProfile);
    }

    void initActions() {
        btnEditProfile.setOnClickListener(this);
    }

    void initStuff() {
        CircleImageView imageView = findViewById(R.id.imgProfile1);
        Glide
                .with(fa)
                .load(mUser.getPhotoUrl())
                .placeholder(R.drawable.blank_user)
                .into(imageView);


        System.out.println(mUser.getPhotoUrl());
        if (!TextUtils.isEmpty(mUser.getDisplayName()))
            txtName.setText(mUser.getDisplayName());

        txtUsername.setText(mUser.getUid());
        txtEmail.setText(mUser.getEmail());
    }

    void showToolbar() {
        toolBar = new SetUpToolBar(fa, true, "Perfil", null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEditProfile:
                startActivity(new Intent(fa, EditProfileActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}