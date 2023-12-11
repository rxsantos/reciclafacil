package com.pucpr.greencycle.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.ContactDatabase;

public class ApresentacaoCliente extends AppCompatActivity {

    TextView Name;
    Cursor cursor;
    String NameHolder, EmailHolder, IdentHolder="",idlogin, CpfHolder, PhoneHolder, EstadoHolder,
            CidadeHolder, EnderecoHolder, CepHolder, PaisHolder, TipoResiduoHolder,DescResiduoHolder;
    int IdHolder;

    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_cliente);
        setTitle("Activity Tela Abertura Cliente");
        Name = findViewById(R.id.tvClientName);


        database = new ContactDatabase(this);
        /*
        ArrayList<Client> clients = database.getClientsFromDB();
        for (Client c:clients) {
            c.print();
        }
        */

        Bundle extras = getIntent().getExtras();
        NameHolder = extras.getString("EXTRA_NAME");
        EmailHolder = extras.getString("EXTRA_EMAIL");
        IdentHolder = extras.getString("EXTRA_USERID");
        Name.setText("Olá "+ NameHolder+"!");
    }

    //Verificar se o Registro do Cliente já existe
    public void CheckingRegisterAlreadyExistsOrNot(){

        // Abrindo SQLite database com permissoes de leitura.
        sqLiteDatabaseObj = database.getReadableDatabase();

        //System.out.println(EmailHolder);
        // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Cliente",null, ""+"email"+"=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // Storing data associated with entered email Cliente table.
                IdHolder = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                idlogin = cursor.getString(cursor.getColumnIndexOrThrow("client_idlogin"));
                NameHolder = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                EmailHolder = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                CpfHolder = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                PhoneHolder = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                EstadoHolder = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                CidadeHolder = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                EnderecoHolder = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                CepHolder = cursor.getString(cursor.getColumnIndexOrThrow("zipcode"));
                PaisHolder = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                TipoResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                DescResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));

                //Fechando cursor.
                //cursor.close();

            }
        }

    }

    public void formClButtonOnClick(View v){
        CheckingRegisterAlreadyExistsOrNot();
        Intent intent = new Intent(ApresentacaoCliente.this, FormCliente.class);
        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID",IdHolder);
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        extras.putString("EXTRA_USERID",IdentHolder);
        extras.putString("EXTRA_CPF",CpfHolder);
        extras.putString("EXTRA_PHONE",PhoneHolder);
        extras.putString("EXTRA_STATE",EstadoHolder);
        extras.putString("EXTRA_CITY",CidadeHolder);
        extras.putString("EXTRA_ADDRESS",EnderecoHolder);
        extras.putString("EXTRA_ZIPCODE",CepHolder);
        extras.putString("EXTRA_COUNTRY",PaisHolder);
        extras.putString("EXTRA_RESIDUO",TipoResiduoHolder);
        extras.putString("EXTRA_DESCRESIDUO",DescResiduoHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void solicitarButtonOnClick(View v){
        CheckingRegisterAlreadyExistsOrNot();
        Intent intent = new Intent(ApresentacaoCliente.this, FormPedidos.class);
        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID",IdHolder);
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void listaButtonOnClick(View v){
        CheckingRegisterAlreadyExistsOrNot();
        Intent intent = new Intent(ApresentacaoCliente.this, ListaPedidos.class);
        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID",IdHolder);
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }
}