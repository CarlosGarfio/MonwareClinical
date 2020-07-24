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

//        String desc = "Clinic, an organized medical service offering diagnostic, therapeutic, or preventive outpatient services. Often, the term covers an entire medical teaching centre, including the hospital and the outpatient facilities. The medical care offered by a clinic may or may not be connected with a hospital.";
//        clinic = new Clinic("Garfio's home", desc, "8:00", "22:00", "Villa de Antares", "Chihuahua", "Chihuahua", "9728", "4987744");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

//        mDatabase.child(context.getString(R.string.fb_table_clinic)).setValue(clinic)
//                .addOnSuccessListener(aVoid -> System.out.println(true))
//                .addOnFailureListener(e -> System.out.println(false));

//        mDatabase.child(context.getString(R.string.fb_table_clinic_books))
//                .child("23-07-2020")
//                .child("pm6gJhavjHMKrCq5niMqVt2dsEI3")
//                .setValue("8:00 - 8:30");

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
