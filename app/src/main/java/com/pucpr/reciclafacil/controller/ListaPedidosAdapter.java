package com.pucpr.reciclafacil.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.model.DataModel;
import com.pucpr.reciclafacil.model.Request;

public class ListaPedidosAdapter extends RecyclerView.Adapter<ListaPedidosAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRequestEmpresa,tvRequestName, tvRequestCnpj, tvRequestEmail,tvRequestPhone,tvRequestAddress,tvRequestZipcode, tvRequestCity,
                 tvRequestState, tvRequestRegion,tvRequestResiduo,tvRequestDescResiduo,tvRequestContato,tvRequestData,tvRequestHora;
        public ViewHolder(View listaView){
            super(listaView);
            tvRequestName = listaView.findViewById(R.id.tvRequestName);
            tvRequestEmail = listaView.findViewById(R.id.tvRequestEmail);
            tvRequestResiduo = listaView.findViewById(R.id.tvRequestResiduo);
            tvRequestDescResiduo = listaView.findViewById(R.id.tvRequestDescResiduo);
            tvRequestEmpresa = listaView.findViewById(R.id.tvRequestEmpresa);
            tvRequestContato = listaView.findViewById(R.id.tvRequestContato);
            tvRequestData = listaView.findViewById(R.id.tvRequestData);
            tvRequestHora = listaView.findViewById(R.id.tvRequestHora);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listaView = inflater.inflate(
                R.layout.recycler_view_list_requests_schedule,
                parent,
                false);
                return new ViewHolder(listaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Request r = DataModel.getInstance().getRequestClient(position);
        holder.tvRequestName.setText(r.getClient_name());
        holder.tvRequestEmail.setText(r.getClient_email());
        holder.tvRequestResiduo.setText(r.getResiduo());
        holder.tvRequestDescResiduo.setText(r.getDescresiduo());
        holder.tvRequestEmpresa.setText(r.getCompany_name());
        holder.tvRequestContato.setText(r.getCompany_contact());
        holder.tvRequestData.setText(r.getDate());
        holder.tvRequestHora.setText(r.getHour());
        //holder.tvPedidoState.setText(c.getState());
        //holder.tvPedidoRegion.setText(c.getRegion());
        //holder.tvPedidoResiduo.setText(c.getResiduo());
        //holder.tvPedidoAgenda.setText(CompanyContact);
        //holder.tvPedidoData.setText(CompanyDate);
        //holder.tvPedidoHora.setText(CompanyHour);
        Log.v("Request: ", "Pos["+position+"]:"+r.getClient_email() + " Email: "+r.getClient_email());

    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getRequestClSize();
    }


}
