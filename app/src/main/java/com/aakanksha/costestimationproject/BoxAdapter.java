package com.aakanksha.costestimationproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.BoxViewHolder> {

    private List<Box> boxList;

    public BoxAdapter(List<Box> boxList) {
        this.boxList = boxList;
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
        holder.boxCost.setText("₹" + box.getCost());
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
