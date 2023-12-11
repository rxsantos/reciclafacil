package com.pucpr.greencycle.controller;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.DataModel;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {


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


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        Spinner spinner;

        public ViewHolder(View itemView){
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            spinner = (Spinner)itemView.findViewById(R.id.spinner);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (clickListener != null){
                        clickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
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
        View itemView = inflater.inflate(
                R.layout.activity_listusers_admin,
                parent,
                false
        );

        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact c = DataModel.getInstance().getContact(position);
        holder.textView1.setText(c.getName());
        holder.textView2.setText(c.getEmail());
        holder.textView3.setText(c.getPassword());
        holder.textView4.setText(c.getOp());
        Log.v("Usuario: ", "Pos["+position+"]:"+c.getName());

    }
    public int getItemCount(){
        return DataModel.getInstance().getContactSize();
    }
}
