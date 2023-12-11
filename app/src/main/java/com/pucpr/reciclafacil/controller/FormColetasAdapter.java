package com.pucpr.reciclafacil.controller;

import android.content.Context;
import android.graphics.Color;
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

public class FormColetasAdapter extends RecyclerView.Adapter<FormColetasAdapter.ViewHolder>{
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClick(View view,int position);
    }
    private FormColetasAdapter.OnItemClickListener clickListener;
    private FormColetasAdapter.OnItemLongClickListener longClickListener;
    public void setOnItemClickListener(FormColetasAdapter.OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setOnItemLongClickListener(FormColetasAdapter.OnItemLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    private Context context;
    int selectionPosition = -1, index;
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvColetaEmpresa,tvColetaName, tvColetaEmail, tvColetaPhone, tvColetaAddress, tvColetaZipcode,
                tvColetaCity,tvColetaState,tvColetaResiduo,tvColetaDescResiduo, tvColetaContato,tvColetaData,tvColetaHora;
        public ViewHolder(View coletaView){
            super(coletaView);
            tvColetaEmpresa = coletaView.findViewById(R.id.tvColetaEmpresa);
            tvColetaName = coletaView.findViewById(R.id.tvColetaName);
            tvColetaEmail = coletaView.findViewById(R.id.tvColetaEmail);
            tvColetaPhone = coletaView.findViewById(R.id.tvColetaPhone);
            tvColetaAddress = coletaView.findViewById(R.id.tvColetaEndereco);
            tvColetaZipcode = coletaView.findViewById(R.id.tvColetaCep);
            tvColetaCity = coletaView.findViewById(R.id.tvColetaCidade);
            tvColetaState = coletaView.findViewById(R.id.tvColetaEstado);
            //tvColetaCountry = coletaView.findViewById(R.id.tvColetaCountry);
            tvColetaResiduo = coletaView.findViewById(R.id.tvColetaResiduo);
            tvColetaDescResiduo = coletaView.findViewById(R.id.tvColetaDescResiduo);
            tvColetaContato = coletaView.findViewById(R.id.tvColetaContato);
            tvColetaData = coletaView.findViewById(R.id.tvColetaData);
            tvColetaHora = coletaView.findViewById(R.id.tvColetaHora);

            coletaView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(view, getAdapterPosition());
                        selectionPosition = getAdapterPosition();
                    }
                }
            });
            coletaView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (longClickListener != null){
                        return longClickListener.onItemLongClick(view, getAdapterPosition());

                    }
                    return false;
                }
            });

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View coletaView = inflater.inflate(
                R.layout.recycler_view_client_requests_available,
                parent,
                false);
        return new ViewHolder(coletaView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Client c = DataModel.getInstance().getClient(position);
        Request r = DataModel.getInstance().getRequestCompany(position);
        holder.tvColetaName.setText(r.getClient_name());
        //holder.tvColetaCpf.setText(c.getCpf());
        holder.tvColetaEmail.setText(r.getClient_email());
        //holder.tvColetaPhone.setText(c.getPhone());
        //holder.tvColetaAddress.setText(c.getAddress());
        //holder.tvColetaZipcode.setText(c.getZipcode());
        //holder.tvColetaCity.setText(c.getCity());
        holder.tvColetaResiduo.setText(r.getResiduo());
        holder.tvColetaDescResiduo.setText(r.getDescresiduo());
        holder.tvColetaEmpresa.setText(r.getCompany_name());
        holder.tvColetaContato.setText(r.getCompany_contact());
        holder.tvColetaData.setText(r.getDate());
        holder.tvColetaHora.setText(r.getHour());

        Log.v("Coleta: ", "Pos["+position+"]:"+r.getClient_name()+" Desc_Residuo: " +r.getDescresiduo());
        if (selectionPosition == position){
            //holder.itemView.setBackgroundColor(Color.rgb(106,189,33));
            holder.itemView.setBackgroundColor(Color.rgb(168,232,185));
        }else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getRequestCpSize();
    }
    public Long getSelected(){
        if (selectionPosition != -1){
            Request c = DataModel.getInstance().getRequestCompany(selectionPosition);
            //return c.getClient_email();
            return c.getId();
        }
        return null;
    }


}
