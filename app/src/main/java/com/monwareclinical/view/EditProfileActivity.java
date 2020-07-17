package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.monwareclinical.R;
import com.monwareclinical.util.SetUpToolBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements
        View.OnClickListener {

    Activity fa;

    SetUpToolBar toolBar;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    CircleImageView imgProfile;
    TextView txtName;
    TextView txtUsername;
    FloatingActionButton btnEditImgProfile;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        fa = this;
        initComps();
        initActions();
        initStuff();
        showToolbar();
    }

    void initComps() {
        fa = this;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//        imgProfile = findViewById(R.id.imgProfile);
//        txtName = findViewById(R.id.txtName);
//        txtUsername = findViewById(R.id.txtUsername);

        btnSave = findViewById(R.id.btnSave);
    }

    void initActions() {
        btnSave.setOnClickListener(this);
    }

    void initStuff() {
//        Picasso.with(fa).load(mUser.getPhotoUrl()).placeholder(getDrawable(R.drawable.blank_user)).into(imgProfile);

//        if (!TextUtils.isEmpty(mUser.getDisplayName()))
//            txtName.setText(mUser.getDisplayName());
//
//        txtUsername.setText(mUser.getUid());
    }

    void showToolbar() {
        toolBar = new SetUpToolBar(fa, true, "Editar Perfil", null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName("Carlos Daniel Garfio Murillo")
                        .setPhotoUri(Uri.parse("https://cdn.pixabay.com/photo/2020/07/14/19/33/alpaca-5405469_960_720.jpg"))
                        .build();
                mUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(task1 -> {
                            onBackPressed();
                        });
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}