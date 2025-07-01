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
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewClients);
        fab = findViewById(R.id.fabAddClient);
        dbHelper = new DatabaseHelper(this);

        // Load clients from DB
        clients = dbHelper.getAllClients();

        adapter = new ClientAdapter(clients, this); // Pass context
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(view -> showAddClientDialog());
    }

    private void showAddClientDialog() {
        final EditText input = new EditText(this);
        input.setHint("Enter client name");
        input.setPadding(40, 40, 40, 40);

        new AlertDialog.Builder(this)
                .setTitle("Add New Client")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {
                    String clientName = input.getText().toString().trim();

                    if (!clientName.isEmpty()) {
                        int newClientId = dbHelper.insertClientAndReturnId(clientName);

                        if (newClientId != -1) {
                            clients.add(new Client(newClientId, clientName));
                            adapter.notifyItemInserted(clients.size() - 1);
                            Toast.makeText(MainActivity.this, "Client added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error saving client", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
