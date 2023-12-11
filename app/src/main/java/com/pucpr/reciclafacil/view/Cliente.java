package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.controller.ClienteAdapter;
import com.pucpr.reciclafacil.model.ContactDatabase;
import com.pucpr.reciclafacil.model.DataModel;

public class Cliente extends AppCompatActivity {
    ContactDatabase database;
    ClienteAdapter adapter = new ClienteAdapter();
    RecyclerView recyclerViewCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_admin);
        setTitle("Activity Cliente");
        recyclerViewCliente = findViewById(R.id.recyclerViewCliente);
        DataModel.getInstance().createClientDatabase(Cliente.this);
        recyclerViewCliente.setAdapter(adapter);
        recyclerViewCliente.setLayoutManager(
                new LinearLayoutManager(Cliente.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                Cliente.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewCliente.addItemDecoration(itemDecoration);


    }
}