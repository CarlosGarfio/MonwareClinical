package com.monwareclinical.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.monwareclinical.R;
import com.monwareclinical.adapter.HoursAdapter;
import com.monwareclinical.dialogs.LoadingDialog;
import com.monwareclinical.model.Clinic;
import com.monwareclinical.model.Hour;
import com.monwareclinical.util.Constants;
import com.monwareclinical.util.SetUpToolBar;
import com.ncorti.slidetoact.SlideToActView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MakeAppointmentActivity extends AppCompatActivity implements
        View.OnClickListener,
        SlideToActView.OnSlideCompleteListener,
        HoursAdapter.SelectIconListener,
        ValueEventListener {

    final String ZERO = "0";
    final String SEPARATOR = "-";
    @SuppressLint("SimpleDateFormat")
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    Activity fa;
    FirebaseUser mUser;
    SetUpToolBar toolBar;
    SlideToActView slider;

    EditText etTitle;
    EditText etDate;
    RecyclerView recyclerView;
    HoursAdapter hoursAdapter;

    final Calendar c = Calendar.getInstance();
    final int mDay = c.get(Calendar.DAY_OF_MONTH);
    final int mMonth = c.get(Calendar.MONTH);
    final int mYear = c.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        initComps();
        initActions();
        initStuff();
        initRecycler();
    }

    void initComps() {
        fa = this;
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        etTitle = findViewById(R.id.etTitle);
        etDate = findViewById(R.id.etDate);
        recyclerView = findViewById(R.id.recyclerView);

        slider = findViewById(R.id.sbConfirmation);
    }

    void initActions() {
        etTitle.setOnClickListener(this);
        etDate.setOnClickListener(this);
        slider.setOnSlideCompleteListener(this);
    }

    void initStuff() {
        toolBar = new SetUpToolBar(fa, true, "Agendar cita", null);
        unlockSlider(false);
//        etDate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                onStart();
//            }
//        });
    }

    void initRecycler() {
        hoursAdapter = new HoursAdapter(fa, this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(hoursAdapter);
    }

    void unlockSlider(boolean bool) {
        if (bool) {
            slider.setOuterColor(getColor(R.color.colorSliderUnlocked));
            slider.setIconColor(getColor(R.color.colorSliderUnlocked));
            slider.setLocked(false);
        } else {
            slider.setOuterColor(getColor(R.color.colorSliderLocked));
            slider.setIconColor(getColor(R.color.colorSliderLocked));
            slider.setLocked(true);
        }
    }

    void loadHours(String selectedDate) throws ParseException {
        LoadingDialog loadingDialog = new LoadingDialog(fa);
        loadingDialog.showDialog();
        loadingDialog.setText("Cargando, porfavor espera...");

        List<Hour> hours = new ArrayList<>();

        Clinic clinic = Constants.getInstance(fa).getClinic();

        String opensAt = selectedDate + " " + clinic.getOpensAt();
        String closesAt = selectedDate + " " + clinic.getClosesAt();

        Date date;

        date = sdf.parse(opensAt);
        Calendar openCalendar = Calendar.getInstance();
        openCalendar.setTime(date);

        date = sdf.parse(closesAt);
        Calendar closesCalendar = Calendar.getInstance();
        closesCalendar.setTime(date);

        long workableHours = openCalendar.getTimeInMillis() - closesCalendar.getTimeInMillis();
        int totalHours = (int) ((workableHours / (1000 * 60 * 60)) % 24);

        if (totalHours < 0)
            totalHours *= -1;

        final int th = (int) (totalHours / .5);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child(getString(R.string.fb_table_clinic_books))
                .child(etDate.getText().toString());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Hour> hoursTook = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String hour = ds.getValue().toString();
                    hoursTook.add(new Hour(hour, Hour.TOOK));
                }

                for (int i = 0; i < th; i++) {
                    int h = openCalendar.get(Calendar.HOUR_OF_DAY);
                    int m = openCalendar.get(Calendar.MINUTE);

                    openCalendar.add(Calendar.MINUTE, 30);

                    int h1 = openCalendar.get(Calendar.HOUR_OF_DAY);
                    int m1 = openCalendar.get(Calendar.MINUTE);

                    String h_m;
                    String h_m1;

                    if (m == 0) {
                        h_m = h + ":00";
                    } else {
                        h_m = h + ":" + m;
                    }
                    if (m1 == 0) {
                        h_m1 = h1 + ":00";
                    } else {
                        h_m1 = h1 + ":" + m1;
                    }

                    String tmp = h_m + " - " + h_m1;

                    if (isHourTook(hoursTook, tmp)) {
                        hours.add(new Hour(tmp, Hour.TOOK));
                    } else {
                        hours.add(new Hour(tmp, Hour.AVAILABLE));
                    }
                }
                loadingDialog.dismissDialog();
                hoursAdapter.setHours(hours);
            }

            boolean isHourTook(List<Hour> hoursTook, String hour) {
                for (Hour h : hoursTook)
                    if (h.getHour().equals(hour))
                        return true;
                return false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etTitle:
                break;
            case R.id.etDate:
                DatePickerDialog.OnDateSetListener dateListener = (datePicker, year, month, dayOfMonth) -> {
                    final int actualMonth = month + 1;
                    String formattedDay = (dayOfMonth < 10) ? ZERO + dayOfMonth : String.valueOf(dayOfMonth);
                    String formattedMonth = (actualMonth < 10) ? ZERO + actualMonth : String.valueOf(actualMonth);
                    String formattedDate = formattedDay + SEPARATOR + formattedMonth + SEPARATOR + year;
                    etDate.setText(formattedDate);
                    try {
                        loadHours(formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                };

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateListener, mYear, mMonth, mDay);

                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());

                datePickerDialog.getDatePicker().setMinDate(cal.getTime().getTime());
                cal.add(Calendar.DAY_OF_MONTH, 7);
                datePickerDialog.getDatePicker().setMaxDate(cal.getTime().getTime());

                datePickerDialog.show();
                break;
        }
    }

    @Override
    public void onSlideComplete(SlideToActView slideToActView) {
        if (slider.isLocked()) {
            slider.resetSlider();
        } else {
            // Make Appointment
        }
    }

    @Override
    public void onHourSelected(int position) {
        Hour hour = hoursAdapter.getHourByPosition(position);
        switch (hour.getState()) {
            case Hour.TOOK:
                Toast.makeText(fa, "Esa hora se encuentra ocupada", Toast.LENGTH_SHORT).show();
                break;
            case Hour.AVAILABLE:
            case Hour.SELECTED:
                hoursAdapter.cleanSelectedHours();
                hoursAdapter.selectHour(position);
                break;
        }
    }

    DatabaseReference mListener;

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String date = etDate.getText().toString().trim();
        if (!TextUtils.isEmpty(date)) {
            try {
                loadHours(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        String date = etDate.getText().toString().trim();
//        if (!TextUtils.isEmpty(date)) {
//            mListener = FirebaseDatabase.getInstance()
//                    .getReference()
//                    .child(getString(R.string.fb_table_clinic_books))
//                    .child(etDate.getText().toString());
//            mListener.addValueEventListener(this);
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mListener != null) mListener.removeEventListener(this);
//    }
}