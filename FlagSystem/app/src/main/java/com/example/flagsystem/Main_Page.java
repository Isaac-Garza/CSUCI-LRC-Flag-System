package com.example.flagsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_Page extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_page);
        CardView pageTutorButton = findViewById(R.id.pageTutor);

        pageTutorButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(this,Subject_Selection.class);
        startActivity(i);
    }
}