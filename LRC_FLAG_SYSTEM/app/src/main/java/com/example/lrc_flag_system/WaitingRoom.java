package com.example.lrc_flag_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WaitingRoom extends AppCompatActivity implements View.OnClickListener {

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

        CardView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Table");
        reference.child(tableFlagged.getTableNumber()).removeValue();

        Intent i = new Intent(this,Subject.class);
        startActivity(i);
    }
    
}