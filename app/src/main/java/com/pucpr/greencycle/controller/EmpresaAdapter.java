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
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.DataModel;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvempName;
        TextView tvempCnpj;
        TextView tvempEmail;
        TextView tvempPhone;
        TextView tvempAddress;
        TextView tvempZipcode;
        TextView tvempCity;
        TextView tvempState;
        TextView tvempRegion;
        TextView tvempResiduo;
        public ViewHolder(View companyView){
            super(companyView);
            tvempName = companyView.findViewById(R.id.tvempName);
            tvempCnpj = companyView.findViewById(R.id.tvempCnpj);
            tvempEmail = companyView.findViewById(R.id.tvempEmail);
            tvempPhone = companyView.findViewById(R.id.tvempPhone);
            tvempAddress = companyView.findViewById(R.id.tvempAddress);
            tvempZipcode = companyView.findViewById(R.id.tvempZipcode);
            tvempCity = companyView.findViewById(R.id.tvempCity);
            tvempState = companyView.findViewById(R.id.tvempState);
            tvempRegion = companyView.findViewById(R.id.tvempRegion);
            tvempResiduo = companyView.findViewById(R.id.tvempResiduo);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View companyView = inflater.inflate(R.layout.recycler_view_lista_empresas,
                parent,
                false);
        return new ViewHolder(companyView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Company c = DataModel.getInstance().getCompany(position);
        holder.tvempName.setText(c.getName());
        holder.tvempCnpj.setText(c.getCnpj());
        holder.tvempEmail.setText(c.getEmail());
        holder.tvempPhone.setText(c.getPhone());
        holder.tvempAddress.setText(c.getAddress());
        holder.tvempZipcode.setText(c.getZipcode());
        holder.tvempCity.setText(c.getCity());
        holder.tvempState.setText(c.getState());
        holder.tvempRegion.setText(c.getRegion());
        holder.tvempResiduo.setText(c.getResiduo());
        Log.v("Company: ", "Pos["+position+"]: "+c.getName() + " IDPK: "+c.getIdpk());

    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getCompanySize();
    }
}
