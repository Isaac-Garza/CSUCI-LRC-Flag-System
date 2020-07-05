package com.example.lrc_flag_system;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Subject extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        CardView mathButton = findViewById(R.id.mathButton);
        CardView chemButton = findViewById(R.id.chemButton);
        CardView bus_econButton = findViewById(R.id.bus_econButton);
        CardView bioButton = findViewById(R.id.bio_button);
        CardView phyButton = findViewById(R.id.physButton);
        CardView physch_socButton = findViewById(R.id.physch_sociButton);
        CardView healthButton = findViewById(R.id.heathsciButton);
        CardView compButton = findViewById(R.id.compButton);
        CardView nursingButton = findViewById(R.id.nursingButton);
        CardView otherButton = findViewById(R.id.otherButton);


        mathButton.setOnClickListener(this);
        chemButton.setOnClickListener(this);
        bus_econButton.setOnClickListener(this);
        bioButton.setOnClickListener(this);
        phyButton.setOnClickListener(this);
        physch_socButton.setOnClickListener(this);
        healthButton.setOnClickListener(this);
        compButton.setOnClickListener(this);
        nursingButton.setOnClickListener(this);
        otherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(this,WaitingRoom.class);
        startActivity(i);
    }
}
