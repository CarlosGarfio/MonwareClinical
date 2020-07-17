package com.monwareclinical.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.monwareclinical.R;
import com.monwareclinical.adapter.PlacesAdapter;
import com.monwareclinical.model.Place;

public class PlaceActivity extends AppCompatActivity implements
        View.OnClickListener {

    Activity fa;

    Place place;

    TextView txtDescription;

    Button btnMakeAnAppointment;
    Button btnPhoneNumber;
    Button btnLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        place = (Place) getIntent().getSerializableExtra(PlacesAdapter.EXTRA_PLACE);
        fa = this;
        txtDescription = findViewById(R.id.txtDescription);

        btnMakeAnAppointment = findViewById(R.id.btnMakeAnAppointment);
        btnPhoneNumber = findViewById(R.id.btnPhoneNumber);
        btnLocation = findViewById(R.id.btnLocation);

        btnMakeAnAppointment.setOnClickListener(this);
        btnPhoneNumber.setOnClickListener(this);
        btnLocation.setOnClickListener(this);

        String description = place.getStreetAddress() + " #" + place.getExtNumber() + "\n" +
                place.getState() + ", " + place.getCity();

        txtDescription.setText(description);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMakeAnAppointment:
                startActivity(new Intent(fa, MakeAppointmentActivity.class));
                break;
            case R.id.btnPhoneNumber:
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + place.getPhoneNumber()));
                startActivity(i);
                break;
            case R.id.btnLocation:
                String streetAddress = place.getStreetAddress();
                String extNumber = place.getExtNumber();

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + streetAddress + " " + extNumber);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(mapIntent);
                else
                    Toast.makeText(fa, "No se encontro la aplicación Google Maps", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}