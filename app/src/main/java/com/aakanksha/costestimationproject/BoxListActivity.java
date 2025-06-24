package com.aakanksha.costestimationproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

        // Show form when FAB is clicked
        fabAddBox.setOnClickListener(v -> showAddBoxFormDialog());
    }

    private void showAddBoxFormDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.add_box_dialog, null);

        EditText inputBoxName = dialogView.findViewById(R.id.inputBoxName);
        EditText inputLength = dialogView.findViewById(R.id.inputLength);
        EditText inputHeight = dialogView.findViewById(R.id.inputHeight);
        EditText inputFlutePapers = dialogView.findViewById(R.id.inputFlutePapers);
        EditText inputPlainPapers = dialogView.findViewById(R.id.inputPlainPapers);
        EditText inputPaperCost = dialogView.findViewById(R.id.inputPaperCost);
        EditText inputQualityFactor = dialogView.findViewById(R.id.inputQualityFactor);

        new AlertDialog.Builder(this)
                .setTitle("Add New Box")
                .setView(dialogView)
                .setPositiveButton("Calculate", (dialog, which) -> {
                    // For now just display a toast, later we'll calculate cost
                    String boxName = inputBoxName.getText().toString().trim();
                    Toast.makeText(this, "Box \"" + boxName + "\" submitted (cost coming soon)", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
