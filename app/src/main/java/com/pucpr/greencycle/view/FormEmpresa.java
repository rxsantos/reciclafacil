package com.pucpr.greencycle.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.util.AlterarEmpresaAsyncTask;
import com.pucpr.greencycle.util.IncluirEmpresaAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormEmpresa extends AppCompatActivity {

    Button btnCompany;
    RadioButton Evidro, Eplastico, Emetal, Epapel, Eoutros;
    TextView editTextEmailEmp, editTextNameEmp;
    String idlogin, EmailHolder,TipoReciclavelEmpresa;
    String F_Result = "Usuário não Encontrado";
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    int index,idpk;
    Cursor cursor;

    EditText

            editTextCnpj,
            editTextPhoneEmp,
            editTextUfEmp,
            editTextCityEmp,
            editTextAddressEmp,
            editTextPostalEmp,
            editTextCountryEmp,
            editTextRegiaoEmp,
            editTextResiduoEmp;
    Date dataAtual = Calendar.getInstance().getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    String date = format.format(dataAtual);
    ContactDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_empresa);
        setTitle("Activity Formulário Empresa");


        editTextNameEmp = findViewById(R.id.editTextNameEmp);
        editTextEmailEmp = findViewById(R.id.editTextEmailEmp);
        editTextCnpj = findViewById(R.id.editTextCnpj);
        editTextPhoneEmp = findViewById(R.id.editTextPhoneEmp);
        editTextUfEmp = findViewById(R.id.editTextUfEmp);
        editTextCityEmp = findViewById(R.id.editTextCityEmp);
        editTextAddressEmp = findViewById(R.id.editTextAddressEmp);
        editTextPostalEmp = findViewById(R.id.editTextPostalEmp);
        editTextCountryEmp = findViewById(R.id.editTextCountryEmp);
        editTextRegiaoEmp = findViewById(R.id.editTextRegiaoEmp);
        editTextResiduoEmp = findViewById(R.id.editTextResiduoEmp);
        Evidro = (RadioButton) findViewById(R.id.radioButtonEvidro);
        Eplastico = (RadioButton) findViewById(R.id.radioButtonEplastico);
        Emetal = (RadioButton) findViewById(R.id.radioButtonEmetal);
        Epapel = (RadioButton) findViewById(R.id.radioButtonEpapel);
        Eoutros = (RadioButton) findViewById(R.id.radioButtonEoutros);

        database = new ContactDatabase(this);
        //DataModel.getInstance().createDatabase(FormCliente.this);
        //DataModel.getInstance().createClientDatabase(FormCliente.this);


        ArrayList<Company> companies = database.getCompaniesFromDB();
        for (Company c : companies) {
            c.print();
        }


        Bundle extras = getIntent().getExtras();
        index = (extras.getInt("EXTRA_ID"));
        idlogin = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        String Email = extras.getString("EXTRA_EMAIL");
        String Cnpj = extras.getString("EXTRA_CNPJ");
        String Phone = extras.getString("EXTRA_PHONE");
        String Estado = extras.getString("EXTRA_STATE");
        String Cidade = extras.getString("EXTRA_CITY");
        String Endereco = extras.getString("EXTRA_ADDRESS");
        String Cep = extras.getString("EXTRA_ZIPCODE");
        String Pais = extras.getString("EXTRA_COUNTRY");
        //String Residuo = extras.getString("EXTRA_RESIDUO");
        String DescResiduo = extras.getString("EXTRA_DESCRESIDUO");
        String Region = extras.getString("EXTRA_REGION");
        editTextNameEmp.setText(Name);
        editTextEmailEmp.setText(Email);
        editTextCnpj.setText(Cnpj);
        editTextPhoneEmp.setText(Phone);
        editTextUfEmp.setText(Estado);
        editTextCityEmp.setText(Cidade);
        editTextAddressEmp.setText(Endereco);
        editTextPostalEmp.setText(Cep);
        editTextCountryEmp.setText(Pais);
        editTextResiduoEmp.setText(DescResiduo);
        editTextRegiaoEmp.setText(Region);


        /*
        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CheckEditTextStatus();
                //CheckingEmailAlreadyExistsOrNot();
            }
        });
    }*/
    }


    public void EmptyEditTexBeforeBack(){
        //editTextNameCl.getText().clear();
        //editTextEmailCl.getText().clear();
    }

    public void formEmpresaButtonOnClick(View v) {
        CheckEditTextStatus();
        CheckingEmailAlreadyExistsOrNot();
    }
    public void InsertData(){
        if (EditTextEmptyHolder) {
            String Nome = editTextNameEmp.getText().toString();
            String Email = editTextEmailEmp.getText().toString();
            String Cnpj = editTextCnpj.getText().toString();
            String Phone = editTextPhoneEmp.getText().toString();
            String Estado = editTextUfEmp.getText().toString();
            String Cidade = editTextCityEmp.getText().toString();
            String Endereco = editTextAddressEmp.getText().toString();
            String Cep = editTextPostalEmp.getText().toString();
            String Pais = editTextCountryEmp.getText().toString();
            String DescResiduo = editTextResiduoEmp.getText().toString();
            String Regiao = editTextRegiaoEmp.getText().toString();

            Company c = new Company();
            c.setIdlogin(idlogin);
            c.setName(Nome);
            c.setEmail(Email);
            c.setCnpj(Cnpj);
            c.setPhone(Phone);
            c.setState(Estado);
            c.setCity(Cidade);
            c.setAddress(Endereco);
            c.setZipcode(Cep);
            c.setCountry(Pais);
            c.setResiduo(TipoReciclavelEmpresa);
            c.setDescresiduo(DescResiduo);
            c.setRegion(Regiao);
            c.setDatainc(date);
            database.createCompanyInDB(c);
            //database.createCompanyInDB(new Company(idpk,idlogin, Nome, Email, Cnpj, Phone, Estado, Cidade, Endereco, Cep, Pais, TipoReciclavelEmpresa, DescResiduo, Regiao,date));
            database.close();
            Toast.makeText(this, "Dados Cadastrados com Sucesso! ", Toast.LENGTH_SHORT).show();
            IncluirEmpresaAsyncTask task = new IncluirEmpresaAsyncTask(c, FormEmpresa.this);
            task.execute();
            try {
                Thread.sleep(3500);
                finish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            //Printando mensagem toast se  algum EditText do formulario estiver em branco.
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                /*
            AlertDialog.Builder builder = new AlertDialog.Builder( FormCliente.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.empty_contact_alert_msg);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();

            */
        }
    }

    public void UpdateData(){
        if (EditTextEmptyHolder) {
            String Nome = editTextNameEmp.getText().toString();
            String Email = editTextEmailEmp.getText().toString();
            String Cnpj = editTextCnpj.getText().toString();
            String Phone = editTextPhoneEmp.getText().toString();
            String Estado = editTextUfEmp.getText().toString();
            String Cidade = editTextCityEmp.getText().toString();
            String Endereco = editTextAddressEmp.getText().toString();
            String Cep = editTextPostalEmp.getText().toString();
            String Pais = editTextCountryEmp.getText().toString();
            String DescResiduo = editTextResiduoEmp.getText().toString();
            String Regiao = editTextRegiaoEmp.getText().toString();

            Company c = new Company();
            c.setId(index);
            c.setIdpk(idpk);
            c.setIdlogin(idlogin);
            c.setName(Nome);
            c.setEmail(Email);
            c.setCnpj(Cnpj);
            c.setPhone(Phone);
            c.setState(Estado);
            c.setCity(Cidade);
            c.setAddress(Endereco);
            c.setZipcode(Cep);
            c.setCountry(Pais);
            c.setResiduo(TipoReciclavelEmpresa);
            c.setDescresiduo(DescResiduo);
            c.setRegion(Regiao);
            c.setDatainc(date);

            database.updateCompanyInDB(c);
            database.close();

            Toast.makeText(this, "Dados Atualizados com Sucesso! ", Toast.LENGTH_SHORT).show();

            AlterarEmpresaAsyncTask task = new AlterarEmpresaAsyncTask(c, FormEmpresa.this);
            task.execute();

            try {
                Thread.sleep(3500);
                finish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            //Printando mensagem toast se  algum EditText do formulario estiver em branco.
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                /*
            AlertDialog.Builder builder = new AlertDialog.Builder( FormCliente.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.empty_contact_alert_msg);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();

            */
        }
    }

    public void CheckEditTextStatus(){
        //Obtendo valor de todos EditText e armazenando em variáveis de String.
        String NomeHolder = editTextNameEmp.getText().toString();
        //System.out.println(NomeHolder);
        String EmailHolder = editTextEmailEmp.getText().toString();
        //System.out.println(EmailHolder);
        String CnpjHolder = editTextCnpj.getText().toString();
        //System.out.println(CnpjHolder);
        String PhoneHolder = editTextPhoneEmp.getText().toString();
        //System.out.println(PhoneHolder);
        String EstadoHolder = editTextUfEmp.getText().toString();
        //System.out.println(EstadoHolder);
        String CidadeHolder = editTextCityEmp.getText().toString();
        //System.out.println(CidadeHolder);
        String EnderecoHolder = editTextAddressEmp.getText().toString();
        //System.out.println(EnderecoHolder);
        String CepHolder = editTextPostalEmp.getText().toString();
        //System.out.println(CepHolder);
        String PaisHolder = editTextCountryEmp.getText().toString();
        //System.out.println(PaisHolder);
        String TipoResiduoHolder = editTextResiduoEmp.getText().toString();
        //System.out.println(TipoResiduoHolder);
        String RegionHolder = editTextRegiaoEmp.getText().toString();
        //System.out.println(RegionHolder);

        if (Evidro.isChecked()){
            TipoReciclavelEmpresa = "Vidro";
        } else if (Eplastico.isChecked()) {
            TipoReciclavelEmpresa = "Plástico";
        } else if (Emetal.isChecked()) {
            TipoReciclavelEmpresa = "Metal";
        } else if (Epapel.isChecked()) {
            TipoReciclavelEmpresa = "Papel";
        }else if(Eoutros.isChecked()){
            TipoReciclavelEmpresa = "Outros";
        }else {
            TipoReciclavelEmpresa = "";
        }

        if (TextUtils.isEmpty(NomeHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(CnpjHolder) || TextUtils.isEmpty(PhoneHolder) || TextUtils.isEmpty(EstadoHolder) || TextUtils.isEmpty(CidadeHolder) || TextUtils.isEmpty(EnderecoHolder) || TextUtils.isEmpty(CepHolder) || TextUtils.isEmpty(PaisHolder) || TextUtils.isEmpty(TipoResiduoHolder) || TextUtils.isEmpty(RegionHolder) || TextUtils.isEmpty(TipoReciclavelEmpresa)){
            EditTextEmptyHolder = false;
            //Toast.makeText(this, "Dados não existem!", Toast.LENGTH_SHORT).show();

        } else{
            EditTextEmptyHolder = true;
            //Toast.makeText(this, "Dados  Existem!", Toast.LENGTH_SHORT).show();

        }

    }

    // Verificando se e-mail já existe ou não.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Abrindo SQLite database com permissoes de escrita.
        sqLiteDatabaseObj = database.getReadableDatabase();
        EmailHolder = editTextEmailEmp.getText().toString();
        //System.out.println(EmailHolder);
        // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Empresa",null, ""+"email"+"=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();
                idpk = cursor.getInt(cursor.getColumnIndexOrThrow("idpk"));

                // Se o email já existir então a variável Result será setada com o valor "Email Encontrado".
                F_Result = "Email Encontrado";
                //System.out.println(F_Result);


                //Fechando cursor.
                //cursor.close();

            }
        }

        CheckFinalResult();
    }

    public void CheckFinalResult(){
        // Verificando se email já existe ou não.
        if (F_Result.equalsIgnoreCase("Email Encontrado")){

            // Se email já existir, será realizado update dos dados.
            UpdateData();

        }else{
            // Se o e-mail não existir, as informacoes de registro do usuário serão inseridas no SQLite database.
            InsertData();
        }
        F_Result = "Não Encontrado";
    }

    public void EmptyEditTextAfterDataInsert(){
        editTextCnpj.getText().clear();
        editTextPhoneEmp.getText().clear();
        editTextUfEmp.getText().clear();
        editTextCityEmp.getText().clear();
        editTextAddressEmp.getText().clear();
        editTextPostalEmp.getText().clear();
        editTextCountryEmp.getText().clear();
        editTextResiduoEmp.getText().clear();
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        //EmptyEditTexBeforeBack();
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(FormEmpresa.this);
        builder.setTitle(R.string.attention);
        builder.setMessage(R.string.empty_contact_alert_msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.create().show();

    }

}