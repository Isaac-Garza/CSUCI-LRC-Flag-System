package com.example.lrc_flag_system;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView pageTutorButton = findViewById(R.id.pageTutor);

        pageTutorButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(this,Subject.class);
        startActivity(i);
    }
}