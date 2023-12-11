package com.pucpr.greencycle.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.DataModel;
import com.pucpr.greencycle.model.Request;

public class FormPedidosAdapter extends RecyclerView.Adapter<FormPedidosAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClick(View view,int position);
    }
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    public void setOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }

    private Context context;
    int selectionPosition = -1;
    String CompanyContact,CompanyDate, CompanyHour;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPedidoName, tvPedidoCnpj, tvPedidoEmail,tvPedidoPhone,tvPedidoAddress,tvPedidoZipcode,
                tvPedidoCity, tvPedidoState, tvPedidoRegion,tvPedidoResiduo;



        //ConstraintLayout rowItemClient;
        public ViewHolder(View pedidosView){
            super(pedidosView);
            tvPedidoName = pedidosView.findViewById(R.id.tvPedidoName);
            tvPedidoCnpj = pedidosView.findViewById(R.id.tvPedidoCnpj);
            tvPedidoEmail = pedidosView.findViewById(R.id.tvPedidoEmail);
            tvPedidoPhone = pedidosView.findViewById(R.id.tvPedidoPhone);
            tvPedidoAddress = pedidosView.findViewById(R.id.tvPedidoEndereco);
            //tvPedidoZipcode = pedidosView.findViewById(R.id.tvPedidoZipcode);
            tvPedidoCity = pedidosView.findViewById(R.id.tvPedidoCidade);
            //tvPedidoState = pedidosView.findViewById(R.id.tvPedidoState);
            tvPedidoRegion = pedidosView.findViewById(R.id.tvPedidoRegion);
            tvPedidoResiduo = pedidosView.findViewById(R.id.tvPedidoResiduo);
            //rowItemClient = pedidosView.findViewById(R.id.rowItemClient);

            pedidosView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                       clickListener.onItemClick(view, getAdapterPosition());
                       selectionPosition = getAdapterPosition();
                    }
                }
            });
            pedidosView.setOnLongClickListener(new View.OnLongClickListener() {
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
        View pedidosView = inflater.inflate(
                R.layout.recycler_view_company_available,
                parent,
                false);
        return new ViewHolder(pedidosView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //SetSelected();
        Company c = DataModel.getInstance().getCompany(position);
        holder.tvPedidoName.setText(c.getName());
        holder.tvPedidoCnpj.setText(c.getCnpj());
        holder.tvPedidoEmail.setText(c.getEmail());
        holder.tvPedidoPhone.setText(c.getPhone());
        holder.tvPedidoAddress.setText(c.getAddress());
        //holder.tvPedidoZipcode.setText(c.getZipcode());
        holder.tvPedidoCity.setText(c.getCity());
        //holder.tvPedidoState.setText(c.getState());
        holder.tvPedidoRegion.setText(c.getRegion());
        holder.tvPedidoResiduo.setText(c.getResiduo());
        //holder.tvPedidoAgenda.setText(CompanyContact);
        //holder.tvPedidoData.setText(CompanyDate);
        //holder.tvPedidoHora.setText(CompanyHour);
        Log.v("Company: ", "Pos["+position+"]:"+c.getName() + " Email: "+c.getEmail());
        if (selectionPosition == position){
            //holder.itemView.setBackgroundColor(Color.rgb(106,189,33));
            holder.itemView.setBackgroundColor(Color.rgb(168,232,185));
        }else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getCompanySize();
    }
    public String getSelected(){
        if (selectionPosition != -1){
            Company c = DataModel.getInstance().getCompany(selectionPosition);
            return c.getEmail();
        }
        return null;
    }

}
