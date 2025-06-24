package com.aakanksha.costestimationproject;

import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BoxListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Box> boxList;
    BoxAdapter adapter;
    TextView clientNameText;
    FloatingActionButton fabAddBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_list);

        recyclerView = findViewById(R.id.recyclerViewBoxes);
        clientNameText = findViewById(R.id.clientNameText);
        fabAddBox = findViewById(R.id.fabAddBox);

        // Get client name from previous screen
        String clientName = getIntent().getStringExtra("client_name");
        clientNameText.setText("Boxes ordered by: " + clientName);

        // Hardcoded box list
        boxList = new ArrayList<>();
        boxList.add(new Box("Corrugated Box", 15.5));
        boxList.add(new Box("Rigid Box", 25.0));
        boxList.add(new Box("Folding Carton", 12.75));

        adapter = new BoxAdapter(boxList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // FAB does nothing for now
    }
}
