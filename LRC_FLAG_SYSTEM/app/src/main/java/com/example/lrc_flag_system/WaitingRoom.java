package com.example.lrc_flag_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class WaitingRoom extends AppCompatActivity implements View.OnClickListener {

    TextView tutor1, tutor2, tutor3;
    Calendar calendar;

    TableModel tableFlagged;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        Intent intent = getIntent();
        String button = intent.getStringExtra("pressed_button");
        tableFlagged = intent.getParcelableExtra("TableValue");

        String selectedSubject = "A %s Tutor has been Requested";
        TextView titleName = findViewById(R.id.request);
        selectedSubject = String.format(selectedSubject, button);
        titleName.setText(selectedSubject);



        addAvaliableTutors(button);

        CardView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    private void addAvaliableTutors(String subject) {
        tutor1 = findViewById(R.id.tutor_1);
        tutor2 = findViewById(R.id.tutor_2);
        tutor3 = findViewById(R.id.tutor_3);

        final ArrayList<TextView> tutors = new ArrayList<>();
        tutors.add(tutor1);
        tutors.add(tutor2);
        tutors.add(tutor3);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Schedule");

        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = "error";

        switch (day) {
            case Calendar.SUNDAY:
                currentDay = "SUN";
                break;
            case Calendar.MONDAY:
                currentDay = "MON";
                break;
            case Calendar.TUESDAY:
                currentDay = "TUE";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "WED";
                break;
            case Calendar.THURSDAY:
                currentDay = "THURS";
                break;
            case Calendar.FRIDAY:
                currentDay = "FRI";
                break;
            case Calendar.SATURDAY:
                currentDay = "SAT";
                break;
        }

            reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String hello;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Table");
        reference.child(tableFlagged.getTableNumber()).removeValue();

        Intent intent = new Intent(this,Subject.class);
        intent.putExtra("TableNumber", tableFlagged.getTableNumber());
        startActivity(intent);
    }
    
}