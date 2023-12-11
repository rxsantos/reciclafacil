package com.pucpr.greencycle.view;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.controller.ListaPedidosAdapter;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;
import com.pucpr.greencycle.model.Request;

import java.util.ArrayList;

public class ListaPedidos extends AppCompatActivity {
    ListaPedidosAdapter adapter = new ListaPedidosAdapter();
    Cursor cursor;
    RecyclerView recyclerViewListaPedidos;
    ContactDatabase database;
    String idloginclient, NameClient, EmailClient, idlogincompany, NameCompany, EmailCompany, ContactCompany, Date, Hour, CnpjClient, PhoneClient, EstadoClient,
            CidadeClient, EnderecoClient, CepClient, PaisClient, TipoResiduoClient, DescResiduoClient, EmailHolder,EmailHolderClient;
    String F_Result = "Não Encontrado", F_Result_Company = "Usuário não Encontrado";

    int IndexCompany, IdRequest, IndexClient,index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_listapedidos);
        setTitle("Activity Lista Solicitações de Coletas");
        recyclerViewListaPedidos = findViewById(R.id.recyclerViewListaPedidos);

        Bundle extras = getIntent().getExtras();
        IndexClient = (extras.getInt("EXTRA_ID"));
        //System.out.println(IndexCompany);
        idloginclient = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        EmailHolder = extras.getString("EXTRA_EMAIL");

        database = new ContactDatabase(this);
        DataModel.getInstance().createRequestDatabase(ListaPedidos.this, EmailHolder);
        System.out.println(EmailHolder);
        ArrayList<Request> requests = database.getRequestsClFromDB(EmailHolder);
        for (Request c:requests) {
            c.print();
        }
        recyclerViewListaPedidos.setAdapter(adapter);
        recyclerViewListaPedidos.setLayoutManager(
                new LinearLayoutManager(ListaPedidos.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                ListaPedidos.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewListaPedidos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewListaPedidos.addItemDecoration(itemDecoration);
    }
}
