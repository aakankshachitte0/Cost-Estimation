package com.aakanksha.costestimationproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private List<Client> clientList;

    public ClientAdapter(List<Client> clients) {
        this.clientList = clients;
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new ClientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        Client client = clientList.get(position);
        holder.clientName.setText(client.getName());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView clientName;

        public ClientViewHolder(View itemView) {
            super(itemView);
            clientName = itemView.findViewById(R.id.client_name);
        }
    }
}
