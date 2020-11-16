package com.example.lrc_flag_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WaitingRoom extends AppCompatActivity implements View.OnClickListener {

    TextView tutor1, tutor2, tutor3;
    TextView titleName;
    Calendar calendar;

    TableModel tableFlagged;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String button = intent.getStringExtra("pressed_button");
        tableFlagged = intent.getParcelableExtra("TableValue");
        int textColor = intent.getIntExtra("color_text", 0);

        TextView titleName = findViewById(R.id.request);
        String selectedSubject = "A " + button + " Tutor has been Requested";

        SpannableString spannableString = new SpannableString(selectedSubject);

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(textColor);
        spannableString.setSpan(foregroundColorSpan, 2, button.length() + 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        titleName.setText(spannableString);

        addAvaliableTutors(button);

        CardView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    private void addAvaliableTutors(final String subject) {
        tutor1 = findViewById(R.id.tutor_1);
        tutor2 = findViewById(R.id.tutor_2);
        tutor3 = findViewById(R.id.tutor_3);

        final ArrayList<TextView> tutorsList = new ArrayList<>();
        tutorsList.add(tutor1);
        tutorsList.add(tutor2);
        tutorsList.add(tutor3);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Logged-In");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> tutors = new ArrayList<>();
                TutorModel tutorModel;
                int index = 0;
                for(DataSnapshot ds : snapshot.getChildren()) {
                    tutorModel = ds.getValue(TutorModel.class);
                    if(tutorModel.getSubject().contains(subject)) {
                        String tutor = tutorModel.getName();
                        tutors.add(tutor);
                        tutorsList.get(index).setText(tutors.get(index));
                        index++;
                    }
                }
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