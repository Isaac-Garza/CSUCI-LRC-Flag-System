package com.example.lrc_flag_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WaitingRoom extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);
        Intent intent = getIntent();
        String button = intent.getStringExtra("pressed_button");
        String selectedSubject = "A %s Tutor has been Requested";

        TextView titleName = findViewById(R.id.titleText);
        selectedSubject = String.format(selectedSubject, button);
        titleName.setText(selectedSubject);

        CardView cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,Subject.class);
        startActivity(i);
    }
}