package com.example.lrc_flag_system;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView pageTutorButton;
    Spinner tableSpinner;
    String tableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageTutorButton = findViewById(R.id.pageTutor);
        tableSpinner = findViewById(R.id.tableSpinner);

        pageTutorButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(this,Subject.class);
        tableString = tableSpinner.getSelectedItem().toString();

        if(!tableString.equals("Table Number")) {
            intent.putExtra("TableNumber", tableString);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Please Select Table Number!", Toast.LENGTH_SHORT).show();
        }
    }
}