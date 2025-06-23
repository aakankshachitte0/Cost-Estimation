package com.aakanksha.costestimationproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ClientAdapter adapter;
    ArrayList<Client> clients;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewClients);
        fab = findViewById(R.id.fabAddClient);

        // Create empty list (no hardcoded clients)
        clients = new ArrayList<>();

        adapter = new ClientAdapter(clients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Show form dialog when FAB is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddClientDialog();
            }
        });
    }

    private void showAddClientDialog() {
        // Input field
        final EditText input = new EditText(this);
        input.setHint("Enter client name");
        input.setPadding(40, 40, 40, 40);

        // Dialog box
        new AlertDialog.Builder(this)
                .setTitle("Add New Client")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String clientName = input.getText().toString().trim();
                    if (!clientName.isEmpty()) {
                        clients.add(new Client(clientName)); // add to list
                        adapter.notifyItemInserted(clients.size() - 1); // refresh list
                        Toast.makeText(MainActivity.this, "Client added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
