package com.aakanksha.costestimationproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BoxDetailsActivity extends AppCompatActivity {

    TextView detailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_details);

        detailsText = findViewById(R.id.textBoxDetails);

        // Get data from intent
        String name = getIntent().getStringExtra("box_name");
        double length = getIntent().getDoubleExtra("length", 0);
        double height = getIntent().getDoubleExtra("height", 0);
        int flute = getIntent().getIntExtra("flute_papers", 0);
        int plain = getIntent().getIntExtra("plain_papers", 0);
        double paperCost = getIntent().getDoubleExtra("paper_cost", 0);
        double qualityFactor = getIntent().getDoubleExtra("quality_factor", 0);
        double finalCost = getIntent().getDoubleExtra("final_cost", 0);

        // Format and display
        String details = "📦 Box Name: " + name + "\n" +
                "📏 Length: " + length + " cm\n" +
                "📏 Height: " + height + " cm\n" +
                "📦 Flute Papers: " + flute + "\n" +
                "📦 Plain Papers: " + plain + "\n" +
                "💰 Paper Cost: ₹" + paperCost + "\n" +
                "⭐ Quality Factor: " + qualityFactor + "\n" +
                "🧮 Final Cost: ₹" + finalCost;

        detailsText.setText(details);
    }
}
