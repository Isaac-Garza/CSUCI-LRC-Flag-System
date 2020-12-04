package com.example.lrc_flag_system;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView pageTutorButton;
    Spinner tableSpinner;
    String tableString;
    String tableNumber;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableNumber = getIntent().getStringExtra("TableNumber");
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
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            intent.putExtra("TableNumber", tableString);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Please Select Table Number!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {

    }
}