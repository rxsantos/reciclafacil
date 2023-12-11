package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.controller.ClienteAdapter;
import com.pucpr.reciclafacil.controller.ContactAdapter;
import com.pucpr.reciclafacil.controller.EmpresaAdapter;
import com.pucpr.reciclafacil.model.Contact;
import com.pucpr.reciclafacil.model.DataModel;
import com.pucpr.reciclafacil.util.ReAdicionarUserAsyncTask;
import com.pucpr.reciclafacil.util.DeletarUserAsyncTask;

public class ContactLogin extends AppCompatActivity {
    //RecyclerView recyclerView;
    RecyclerView recyclerView;
    RecyclerView recyclerViewCliente;
    RecyclerView recyclerViewEmpresa;
    ContactAdapter adaptercontact = new ContactAdapter();

    ClienteAdapter adaptercliente = new ClienteAdapter();

    EmpresaAdapter adapterempresa = new EmpresaAdapter();
    Button Adicionar;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_admin);
        setTitle("Activity RecyclerViewAdmin");
        Adicionar = (Button) findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerViewAdmin);
        recyclerViewCliente = findViewById(R.id.recyclerViewCliente);
        recyclerViewEmpresa = findViewById(R.id.recyclerViewEmpresa);

        spinner = findViewById(R.id.spinner);

        DataModel.getInstance().createDatabase(ContactLogin.this);
        DataModel.getInstance().createClientDatabase(ContactLogin.this);
        DataModel.getInstance().createCompanyDatabase(ContactLogin.this);


        //Criar String Array para Spinner
        String [] spinnerList = getResources().getStringArray(R.array.cadastros);
        ArrayAdapter<String> adapterclient = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,spinnerList);
        adapterclient.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapterclient);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    //RecycleView - Contas Usuários
                    recyclerView.setAdapter(adaptercontact);
                    recyclerView.setLayoutManager(
                            new LinearLayoutManager(ContactLogin.this)
                    );
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                            ContactLogin.this,DividerItemDecoration.VERTICAL
                    );
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.addItemDecoration(itemDecoration);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerViewCliente.setVisibility(View.GONE);
                    recyclerViewEmpresa.setVisibility(View.GONE);
                }
                if(position == 1){
                    //RecycleView - Cadastro de Clientes
                    recyclerViewCliente.setAdapter(adaptercliente);
                    recyclerViewCliente.setLayoutManager(
                            new LinearLayoutManager(ContactLogin.this)
                    );
                    RecyclerView.ItemDecoration itemDecorationCliente = new DividerItemDecoration(
                            ContactLogin.this,DividerItemDecoration.VERTICAL
                    );
                    recyclerViewCliente.addItemDecoration(itemDecorationCliente);
                    recyclerView.setVisibility(View.GONE);
                    recyclerViewCliente.setVisibility(View.VISIBLE);
                    recyclerViewEmpresa.setVisibility(View.GONE);
                }
                if (position == 2){
                    //RecycleView - Cadastro de Empresa
                    recyclerViewEmpresa.setAdapter(adapterempresa);
                    recyclerViewEmpresa.setLayoutManager(
                            new LinearLayoutManager(ContactLogin.this)
                    );
                    RecyclerView.ItemDecoration itemDecorationEmpresa = new DividerItemDecoration(
                            ContactLogin.this,DividerItemDecoration.VERTICAL
                    );
                    recyclerViewEmpresa.addItemDecoration(itemDecorationEmpresa);
                    recyclerView.setVisibility(View.GONE);
                    recyclerViewCliente.setVisibility(View.GONE);
                    recyclerViewEmpresa.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        adaptercontact.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToAddUser(position);
            }
        });

        adaptercontact.setOnItemLongClickListener(new ContactAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Contact c = DataModel.getInstance().getContact(position);
                try {
                    DataModel.getInstance().removeContact(position);
                    DeletarUserAsyncTask task = new DeletarUserAsyncTask(c,ContactLogin.this);
                    task.execute();
                    //Thread.sleep(4000);
                }catch (Exception e){
                    Log.e("Adapter", "Erro: "+e.getMessage());
                }




                adaptercontact.notifyItemRemoved(position);
                View contextView = findViewById(android.R.id.content);
                Snackbar.make(contextView,R.string.remove_contact,Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataModel.getInstance().insertContact(c,position);
                                adaptercontact.notifyItemInserted(position);
                                ReAdicionarUserAsyncTask task =
                                        new ReAdicionarUserAsyncTask(c,ContactLogin.this);
                                task.execute();
                            }
                        }).show();
                return true;
            }
        });
        //adaptercontact.notifyDataSetChanged();


    }



    protected void onResume(){
        super.onResume();
        adaptercontact.notifyDataSetChanged();
    }
    void goToAddUser(int index){
        Intent intent = new  Intent(ContactLogin.this, UsersLoginUpdate.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }
    public void addButtonOnClick(View v){
        Intent intent = new  Intent(ContactLogin.this, UsersLoginAdd.class);
        startActivity(intent);
    }


}