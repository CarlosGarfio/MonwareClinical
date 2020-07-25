package com.monwareclinical.util;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monwareclinical.R;
import com.monwareclinical.model.Clinic;

public class Constants {

    public static final String URL_DEFAULT_PROFILE_PHOTO = "https://firebasestorage.googleapis.com/v0/b/monstersoft-8f986.appspot.com/o/default_photos%2Fblank_user.png?alt=media&token=53168e83-7011-4b19-926a-5fb5dba8cce1";

    static Constants instance;
    Context context;

    Clinic clinic;

    Constants(Context context) {
        this.context = context;

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child(context.getString(R.string.fb_table_clinic));

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clinic = snapshot.getValue(Clinic.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static synchronized Constants getInstance(Context context) {
        if (instance == null)
            instance = new Constants(context);
        return instance;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
