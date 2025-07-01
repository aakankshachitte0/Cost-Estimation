package com.aakanksha.costestimationproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.BoxViewHolder> {

    private List<Box> boxList;
    private Context context;  // ✅ Add context for navigation

    public BoxAdapter(List<Box> boxList, Context context) {
        this.boxList = boxList;
        this.context = context;
    }

    @Override
    public BoxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box, parent, false);
        return new BoxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoxViewHolder holder, int position) {
        Box box = boxList.get(position);
        holder.boxName.setText(box.getName());
        holder.boxCost.setText("₹" + String.format("%.2f", box.getFinalCost()));

        // ✅ On click, go to BoxDetailsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BoxDetailsActivity.class);
            intent.putExtra("box_name", box.name);
            intent.putExtra("length", box.length);
            intent.putExtra("height", box.height);
            intent.putExtra("flute_papers", box.flutePapers);
            intent.putExtra("plain_papers", box.plainPapers);
            intent.putExtra("paper_cost", box.paperCost);
            intent.putExtra("quality_factor", box.qualityFactor);
            intent.putExtra("final_cost", box.finalCost);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return boxList.size();
    }

    public static class BoxViewHolder extends RecyclerView.ViewHolder {
        TextView boxName, boxCost;

        public BoxViewHolder(View itemView) {
            super(itemView);
            boxName = itemView.findViewById(R.id.boxName);
            boxCost = itemView.findViewById(R.id.boxCost);
        }
    }
}
