package com.aakanksha.costestimationproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BoxDetailsActivity extends AppCompatActivity {

    TextView txtBoxName, txtLength, txtHeight, txtFlute, txtPlain, txtCost, txtQuality, txtFinalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_details);

        // Step 1: Initialize views
        txtBoxName = findViewById(R.id.txtBoxName);
        txtLength = findViewById(R.id.txtLength);
        txtHeight = findViewById(R.id.txtHeight);
        txtFlute = findViewById(R.id.txtFlute);
        txtPlain = findViewById(R.id.txtPlain);
        txtCost = findViewById(R.id.txtCost);
        txtQuality = findViewById(R.id.txtQuality);
        txtFinalCost = findViewById(R.id.txtFinalCost);

        // Step 2: Get data from Intent
        String name = getIntent().getStringExtra("box_name");
        double length = getIntent().getDoubleExtra("length", 0);
        double height = getIntent().getDoubleExtra("height", 0);
        int flute = getIntent().getIntExtra("flute_papers", 0);
        int plain = getIntent().getIntExtra("plain_papers", 0);
        double paperCost = getIntent().getDoubleExtra("paper_cost", 0);
        double qualityFactor = getIntent().getDoubleExtra("quality_factor", 0);
        double finalCost = getIntent().getDoubleExtra("final_cost", 0);

        // Step 3: Set data to views
        txtBoxName.setText("📦 Box Name: " + name);
        txtLength.setText("✏️ Length: " + length + " cm");
        txtHeight.setText("📐 Height: " + height + " cm");
        txtFlute.setText("📦 Flute Papers: " + flute);
        txtPlain.setText("📦 Plain Papers: " + plain);
        txtCost.setText("💰 Paper Cost: ₹" + paperCost);
        txtQuality.setText("⭐ Quality Factor: " + qualityFactor);
        txtFinalCost.setText("📋 Final Cost: ₹" + finalCost);
    }
}
