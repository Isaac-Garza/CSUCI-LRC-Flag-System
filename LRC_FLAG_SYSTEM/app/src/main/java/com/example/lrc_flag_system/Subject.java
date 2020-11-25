package com.example.lrc_flag_system;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Subject extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String tableNumber;
    TutorModel avaliableSubject;
    List<String> subjects = Arrays.asList("MATH","CHEM","BUS/ECON","BIO","PHY","PHYCH/SOCI","HEALTH SCI","COMP","NURSING");
    HashMap<String, Boolean> avaliableSubjects = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        tableNumber = getIntent().getStringExtra("TableNumber");

        avaliableSubjects.put("MATH", false);
        avaliableSubjects.put("CHEM", false);
        avaliableSubjects.put("BUS/ECON", false);
        avaliableSubjects.put("BIO", false);
        avaliableSubjects.put("PHY", false);
        avaliableSubjects.put("PHYCH/SOCI", false);
        avaliableSubjects.put("HEALTH SCI", false);
        avaliableSubjects.put("COMP", false);
        avaliableSubjects.put("NURSING", false);

        final CardView mathButton = findViewById(R.id.mathButton);
        final CardView chemButton = findViewById(R.id.chemButton);
        final CardView bus_econButton = findViewById(R.id.bus_econButton);
        final CardView bioButton = findViewById(R.id.bio_button);
        final CardView phyButton = findViewById(R.id.physButton);
        final CardView physch_socButton = findViewById(R.id.physch_sociButton);
        final CardView healthButton = findViewById(R.id.heathsciButton);
        final CardView compButton = findViewById(R.id.compButton);
        final CardView nursingButton = findViewById(R.id.nursingButton);
        final CardView otherButton = findViewById(R.id.otherButton);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Logged-In");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                avaliableSubject = new TutorModel();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    avaliableSubject = ds.getValue(TutorModel.class);
                    String[] separatedSubjects = avaliableSubject.getSubject().split(", ");
                    // Make a array of true boolean, set them false and traverse through them
                    for (String tutorSubject : separatedSubjects) {
                        avaliableSubjects.put(tutorSubject, true);
                    }
                }
                Iterator it = avaliableSubjects.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    int faded = ResourcesCompat.getColor(getResources(), R.color.faded, null);
                    if ((Boolean)pair.getValue() == false) {
                        switch (pair.getKey().toString()) {
                            case "MATH":
                                mathButton.setClickable(false);
                                mathButton.setCardBackgroundColor(faded);
                                break;
                            case "CHEM":
                                chemButton.setClickable(false);
                                chemButton.setCardBackgroundColor(faded);
                                break;
                            case "BUS/ECON":
                                bus_econButton.setClickable(false);
                                bus_econButton.setCardBackgroundColor(faded);
                                break;
                            case "BIO":
                                bioButton.setClickable(false);
                                bioButton.setCardBackgroundColor(faded);
                                break;
                            case "PHY":
                                phyButton.setClickable(false);
                                phyButton.setCardBackgroundColor(faded);
                                break;
                            case "PHYCH/SOCI":
                                physch_socButton.setClickable(false);
                                physch_socButton.setCardBackgroundColor(faded);
                                break;
                            case "HEALTH SCI":
                                healthButton.setClickable(false);
                                healthButton.setCardBackgroundColor(faded);
                                break;
                            case "COMP":
                                compButton.setClickable(false);
                                compButton.setCardBackgroundColor(faded);
                                break;
                            case "NURSING":
                                nursingButton.setClickable(false);
                                nursingButton.setCardBackgroundColor(faded);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
