package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.R.id;
import com.pucpr.reciclafacil.model.ContactDatabase;

public class ApresentacaoEmpresa extends AppCompatActivity {

    TextView Name;
    Cursor cursor;
    String NameHolder, EmailHolder, IdentHolder="",idlogin, CnpjHolder, PhoneHolder, EstadoHolder,
            CidadeHolder, EnderecoHolder, CepHolder, PaisHolder, TipoResiduoHolder, DescResiduoHolder, RegionHolder;
    int IdHolder;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_empresa);
        setTitle("Activity Tela Abertura Empresa");
        Name = findViewById(id.tvEmpresaName);
        //DataModel.getInstance().createDatabase(ApresentacaoEmpresa.this);

        database = new ContactDatabase(this);
        /*
        ArrayList<Company> companies = database.getCompaniesFromDB();
        for (Company c:companies) {
            c.print();
        }
        */

        Bundle extras = getIntent().getExtras();
        NameHolder = extras.getString("EXTRA_NAME");
        EmailHolder = extras.getString("EXTRA_EMAIL");
        IdentHolder = extras.getString("EXTRA_USERID");
        Name.setText("Olá "+ NameHolder+"!");
    }

    public void CheckingRegisterAlreadyExistsOrNot(){

        // Abrindo SQLite database com permissoes de leitura.
        sqLiteDatabaseObj = database.getReadableDatabase();

        //System.out.println(EmailHolder);
        // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Empresa",null, ""+"email"+"=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // Storing Password associated with entered email.
                IdHolder = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                idlogin = cursor.getString(cursor.getColumnIndexOrThrow("company_idlogin"));
                NameHolder = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                EmailHolder = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                CnpjHolder = cursor.getString(cursor.getColumnIndexOrThrow("cnpj"));
                PhoneHolder = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                EstadoHolder = cursor.getString(cursor.getColumnIndexOrThrow("state"));
                CidadeHolder = cursor.getString(cursor.getColumnIndexOrThrow("city"));
                EnderecoHolder = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                CepHolder = cursor.getString(cursor.getColumnIndexOrThrow("zipcode"));
                PaisHolder = cursor.getString(cursor.getColumnIndexOrThrow("country"));
                TipoResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                DescResiduoHolder = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));
                RegionHolder = cursor.getString(cursor.getColumnIndexOrThrow("region"));

                //Fechando cursor.
                //cursor.close();
            }
        }

    }
    public void formEmpButtonOnClick(View v){
        CheckingRegisterAlreadyExistsOrNot();
        Intent intent = new Intent(ApresentacaoEmpresa.this, FormEmpresa.class);
        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID",IdHolder);
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        extras.putString("EXTRA_USERID",IdentHolder);
        extras.putString("EXTRA_CNPJ",CnpjHolder);
        extras.putString("EXTRA_PHONE",PhoneHolder);
        extras.putString("EXTRA_STATE",EstadoHolder);
        extras.putString("EXTRA_CITY",CidadeHolder);
        extras.putString("EXTRA_ADDRESS",EnderecoHolder);
        extras.putString("EXTRA_ZIPCODE",CepHolder);
        extras.putString("EXTRA_COUNTRY",PaisHolder);
        extras.putString("EXTRA_RESIDUO",TipoResiduoHolder);
        extras.putString("EXTRA_DESCRESIDUO",DescResiduoHolder);
        extras.putString("EXTRA_REGION",RegionHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }


    public void coletarButtonOnClick(View v){
        CheckingRegisterAlreadyExistsOrNot();
        Intent intent = new Intent(ApresentacaoEmpresa.this, FormColetas.class);
        Bundle extras = new Bundle();
        extras.putInt("EXTRA_ID",IdHolder);
        extras.putString("EXTRA_NAME",NameHolder);
        extras.putString("EXTRA_EMAIL",EmailHolder);
        intent.putExtras(extras);
        startActivity(intent);
    }
}