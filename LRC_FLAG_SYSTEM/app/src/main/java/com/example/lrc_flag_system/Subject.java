package com.example.lrc_flag_system;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Subject extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        tableNumber = getIntent().getStringExtra("TableNumber");

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
    public void onClick(View view)
    {
        Intent intent = new Intent(this,WaitingRoom.class);
        String selectedButton = "";
        int subjectColor = -1;

        switch(view.getId())
        {
            case R.id.mathButton:
                selectedButton = "MATH";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.red, null);
                break;
            case R.id.chemButton:
                selectedButton = "CHEM";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.orange, null);
                break;
            case R.id.bus_econButton:
                selectedButton = "BUS/ECON";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
                break;
            case R.id.bio_button:
                selectedButton = "BIO";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.green, null);
                break;
            case R.id.physButton:
                selectedButton = "PHYS";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.light_blue, null);
                break;
            case R.id.physch_sociButton:
                selectedButton = "PHYCH/SOCI";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.blue, null);
                break;
            case R.id.heathsciButton:
                selectedButton = "HEALTH SCI";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.purple, null);
                break;
            case R.id.compButton:
                selectedButton = "COMP";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.grey, null);
                break;
            case R.id.nursingButton:
                selectedButton = "NURSING";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.pink, null);
                break;
            case R.id.otherButton:
                selectedButton = "OTHER";
                subjectColor = ResourcesCompat.getColor(getResources(), R.color.other_color, null);
                break;
        }

        TableModel extraTable = signalTutor(selectedButton);
        intent.putExtra("color_text", subjectColor);
        intent.putExtra("pressed_button",selectedButton);
        intent.putExtra("TableValue", extraTable);
        startActivity(intent);
    }

    public TableModel signalTutor(String subject) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Table");

        TableModel tableModel = new TableModel(tableNumber, subject);

        reference.child(tableModel.getTableNumber()).setValue(subject);

        return tableModel;
    }
}
