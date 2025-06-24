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
    DatabaseHelper dbHelper;

    int clientId;
    String clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_list);

        recyclerView = findViewById(R.id.recyclerViewBoxes);
        clientNameText = findViewById(R.id.clientNameText);
        fabAddBox = findViewById(R.id.fabAddBox);
        dbHelper = new DatabaseHelper(this);

        // Get client data from intent
        clientName = getIntent().getStringExtra("client_name");
        clientId = getIntent().getIntExtra("client_id", -1); // should be passed from MainActivity

        clientNameText.setText("Boxes ordered by: " + clientName);

        // Load boxes for this client
        boxList = dbHelper.getBoxesForClient(clientId);

        adapter = new BoxAdapter(boxList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Show dialog on FAB click
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
                .setPositiveButton("Save", (dialog, which) -> {
                    try {
                        String name = inputBoxName.getText().toString().trim();
                        double length = Double.parseDouble(inputLength.getText().toString());
                        double height = Double.parseDouble(inputHeight.getText().toString());
                        int flutePapers = Integer.parseInt(inputFlutePapers.getText().toString());
                        int plainPapers = Integer.parseInt(inputPlainPapers.getText().toString());
                        double paperCost = Double.parseDouble(inputPaperCost.getText().toString());
                        double qualityFactor = Double.parseDouble(inputQualityFactor.getText().toString());
                        double finalCost = 0.0; // cost to be calculated later

                        Box newBox = new Box(name, length, height, flutePapers, plainPapers,
                                paperCost, qualityFactor, finalCost, clientId);

                        boolean inserted = dbHelper.insertBox(newBox);

                        if (inserted) {
                            boxList.add(newBox);
                            adapter.notifyItemInserted(boxList.size() - 1);
                            Toast.makeText(BoxListActivity.this, "Box saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BoxListActivity.this, "Failed to save box", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(BoxListActivity.this, "Please enter valid values", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
