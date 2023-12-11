package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.controller.EmpresaAdapter;
import com.pucpr.reciclafacil.model.DataModel;

public class Empresa extends AppCompatActivity {

    EmpresaAdapter adapter = new EmpresaAdapter();
    RecyclerView recyclerViewEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_admin);
        setTitle("Activity Empresa");
        recyclerViewEmpresa = findViewById(R.id.recyclerViewEmpresa);
        DataModel.getInstance().createCompanyDatabase(Empresa.this);
        recyclerViewEmpresa.setAdapter(adapter);
        recyclerViewEmpresa.setLayoutManager(
                new LinearLayoutManager(Empresa.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                Empresa.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewEmpresa.addItemDecoration(itemDecoration);
    }
}