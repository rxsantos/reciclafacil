package com.pucpr.greencycle.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.DataModel;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvclName;
        TextView tvclCpf;
        TextView tvclEmail;
        TextView tvclPhone;
        TextView tvclAddress;
        TextView tvclZipcode;
        TextView tvclCity;
        TextView tvclState;
        //TextView tvclCountry;
        TextView tvclResiduo;

        public ViewHolder(View clientView){
            super(clientView);
            tvclName = clientView.findViewById(R.id.tvclName);
            tvclCpf = clientView.findViewById(R.id.tvclCpf);
            tvclEmail = clientView.findViewById(R.id.tvclEmail);
            tvclPhone = clientView.findViewById(R.id.tvclPhone);
            tvclAddress = clientView.findViewById(R.id.tvclAddress);
            tvclZipcode = clientView.findViewById(R.id.tvclZipcode);
            tvclCity = clientView.findViewById(R.id.tvclCity);
            tvclState = clientView.findViewById(R.id.tvclState);
            //tvclCountry = clientView.findViewById(R.id.tvclCountry);
            tvclResiduo = clientView.findViewById(R.id.tvclResiduo);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View clientView = inflater.inflate(R.layout.recycler_view_lista_clientes,
                parent,
                false);
        return new ViewHolder(clientView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client c = DataModel.getInstance().getClient(position);
        holder.tvclName.setText(c.getName());
        holder.tvclCpf.setText(c.getCpf());
        holder.tvclEmail.setText(c.getEmail());
        holder.tvclPhone.setText(c.getPhone());
        holder.tvclAddress.setText(c.getAddress());
        holder.tvclZipcode.setText(c.getZipcode());
        holder.tvclCity.setText(c.getCity());
        holder.tvclState.setText(c.getState());
        //holder.tvclCountry.setText(c.getCountry());
        holder.tvclResiduo.setText(c.getResiduo());
        Log.v("Client: ", "Pos["+position+"]:"+c.getName());

    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getClientSize();
    }
}
