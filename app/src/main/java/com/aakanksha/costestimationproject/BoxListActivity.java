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

        // ✅ Get client ID and name from intent
        clientId = getIntent().getIntExtra("client_id", -1);
        clientName = getIntent().getStringExtra("client_name");

        clientNameText.setText("Boxes ordered by: " + clientName);

        // ✅ Load boxes from DB for this client
        // Load boxes from DB for this client
        boxList = dbHelper.getBoxesForClient(clientId);
        adapter = new BoxAdapter(boxList, this); // ✅ Pass context
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


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
                    try {
                        String name = inputBoxName.getText().toString().trim();
                        double length = Double.parseDouble(inputLength.getText().toString());
                        double height = Double.parseDouble(inputHeight.getText().toString());
                        int flute = Integer.parseInt(inputFlutePapers.getText().toString());
                        int plain = Integer.parseInt(inputPlainPapers.getText().toString());
                        double paperCost = Double.parseDouble(inputPaperCost.getText().toString());
                        double qualityFactor = Double.parseDouble(inputQualityFactor.getText().toString());

                        // ✅ Create full box and auto-calculate cost
                        Box newBox = new Box(name, length, height, flute, plain, paperCost, qualityFactor, clientId);

                        // ✅ Save to DB
                        boolean saved = dbHelper.insertBox(newBox);
                        if (saved) {
                            boxList.add(newBox);
                            adapter.notifyItemInserted(boxList.size() - 1);
                            Toast.makeText(this, "Box saved! Cost: ₹" + String.format("%.2f", newBox.finalCost), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "Failed to save box", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}