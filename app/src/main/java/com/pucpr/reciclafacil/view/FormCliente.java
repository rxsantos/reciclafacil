package com.pucpr.reciclafacil.view;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pucpr.reciclafacil.R;

import com.pucpr.reciclafacil.model.Client;
import com.pucpr.reciclafacil.model.ContactDatabase;
import com.pucpr.reciclafacil.util.AlterarClienteAsyncTask;
import com.pucpr.reciclafacil.util.IncluirClienteAsyncTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FormCliente extends AppCompatActivity {

    Button btnClient;
    RadioButton Cvidro, Cplastico, Cmetal, Cpapel, Coutros;
    TextView editTextEmailCl, editTextNameCl;
    String idlogin, EmailHolder, TipoReciclavelCliente;
    String F_Result = "Usuário não Encontrado";
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    int index,idpk;
    Cursor cursor;
    EditText
            editTextCpf,
            editTextPhoneCl,
            editTextUfCl,
            editTextCityCl,
            editTextAddressCl,
            editTextPostalCl,
            editTextCountryCl,
            editTextResiduoCl;
    Date dataAtual = Calendar.getInstance().getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = format.format(dataAtual);
ContactDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);
        setTitle("Activity Formulário Cliente");

        btnClient = (Button) findViewById(R.id.buttonSalvaCl);
        editTextNameCl = findViewById(R.id.editTextNameCl);
        editTextEmailCl = findViewById(R.id.editTextEmailCl);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextPhoneCl = findViewById(R.id.editTextPhoneCl);
        editTextUfCl = findViewById(R.id.editTextUfCl);
        editTextCityCl = findViewById(R.id.editTextCityCl);
        editTextAddressCl = findViewById(R.id.editTextAddressCl);
        editTextPostalCl = findViewById(R.id.editTextPostalCl);
        editTextCountryCl = findViewById(R.id.editTextCountryCl);
        editTextResiduoCl = findViewById(R.id.editTextResiduoCl);
        Cvidro = (RadioButton) findViewById(R.id.radioButtonCvidro);
        Cplastico = (RadioButton) findViewById(R.id.radioButtonCplastico);
        Cmetal = (RadioButton) findViewById(R.id.radioButtonCmetal);
        Cpapel = (RadioButton) findViewById(R.id.radioButtonCpapel);
        Coutros = (RadioButton) findViewById(R.id.radioButtonCoutros);


        database = new ContactDatabase(this);
        //DataModel.getInstance().createDatabase(FormCliente.this);
        //DataModel.getInstance().createClientDatabase(FormCliente.this);


        ArrayList<Client> clients = database.getClientsFromDB();
        for (Client c : clients) {
            c.print();
        }


        Bundle extras = getIntent().getExtras();
        index = (extras.getInt("EXTRA_ID"));
        idlogin = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        String Email = extras.getString("EXTRA_EMAIL");
        String Cpf = extras.getString("EXTRA_CPF");
        String Phone = extras.getString("EXTRA_PHONE");
        String Estado = extras.getString("EXTRA_STATE");
        String Cidade = extras.getString("EXTRA_CITY");
        String Endereco = extras.getString("EXTRA_ADDRESS");
        String Cep = extras.getString("EXTRA_ZIPCODE");
        String Pais = extras.getString("EXTRA_COUNTRY");
        String DescResiduo = extras.getString("EXTRA_DESCRESIDUO");
        editTextNameCl.setText(Name);
        editTextEmailCl.setText(Email);
        editTextCpf.setText(Cpf);
        editTextPhoneCl.setText(Phone);
        editTextUfCl.setText(Estado);
        editTextCityCl.setText(Cidade);
        editTextAddressCl.setText(Endereco);
        editTextPostalCl.setText(Cep);
        editTextCountryCl.setText(Pais);
        editTextResiduoCl.setText(DescResiduo);

        /*
        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextStatus();
                CheckingEmailAlreadyExistsOrNot();
            }
        });
    }*/
    }
    public void EmptyEditTexBeforeBack(){
        //editTextNameCl.getText().clear();
        //editTextEmailCl.getText().clear();
    }



    public void formClienteButtonOnClick(View v){
        CheckEditTextStatus();
        CheckingEmailAlreadyExistsOrNot();

    }
    public void InsertData(){
        if (EditTextEmptyHolder) {
            String Nome = editTextNameCl.getText().toString();
            String Email = editTextEmailCl.getText().toString();
            String Cpf = editTextCpf.getText().toString();
            String Phone = editTextPhoneCl.getText().toString();
            String Estado = editTextUfCl.getText().toString();
            String Cidade = editTextCityCl.getText().toString();
            String Endereco = editTextAddressCl.getText().toString();
            String Cep = editTextPostalCl.getText().toString();
            String Pais = editTextCountryCl.getText().toString();
            String DescResiduo = editTextResiduoCl.getText().toString();
            Client c = new Client();
            c.setIdlogin(idlogin);
            c.setName(Nome);
            c.setEmail(Email);
            c.setCpf(Cpf);
            c.setPhone(Phone);
            c.setState(Estado);
            c.setCity(Cidade);
            c.setAddress(Endereco);
            c.setZipcode(Cep);
            c.setCountry(Pais);
            c.setResiduo(TipoReciclavelCliente);
            c.setDescresiduo(DescResiduo);
            c.setDatainc(date);
            database.createClientInDB(c);
            database.close();
            Toast.makeText(this, "Dados Cadastrados com Sucesso! ", Toast.LENGTH_SHORT).show();
            IncluirClienteAsyncTask task = new IncluirClienteAsyncTask(c, FormCliente.this);
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
            String Nome = editTextNameCl.getText().toString();
            String Email = editTextEmailCl.getText().toString();
            String Cpf = editTextCpf.getText().toString();
            String Phone = editTextPhoneCl.getText().toString();
            String Estado = editTextUfCl.getText().toString();
            String Cidade = editTextCityCl.getText().toString();
            String Endereco = editTextAddressCl.getText().toString();
            String Cep = editTextPostalCl.getText().toString();
            String Pais = editTextCountryCl.getText().toString();
            String DescResiduo = editTextResiduoCl.getText().toString();
            /*AlertDialog.Builder builder = new AlertDialog.Builder( FormCliente.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.confirm_alert_msg_company);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();*/
            Client c = new Client();
            c.setId(index);
            c.setIdpk(idpk);
            c.setIdlogin(idlogin);
            c.setName(Nome);
            c.setEmail(Email);
            c.setCpf(Cpf);
            c.setPhone(Phone);
            c.setState(Estado);
            c.setCity(Cidade);
            c.setAddress(Endereco);
            c.setZipcode(Cep);
            c.setCountry(Pais);
            c.setResiduo(TipoReciclavelCliente);
            c.setDescresiduo(DescResiduo);
            c.setDatainc(date);

            database.updateClientInDB(c);
            database.close();
            Toast.makeText(this, "Dados Atualizados com Sucesso!", Toast.LENGTH_SHORT).show();
            AlterarClienteAsyncTask task = new AlterarClienteAsyncTask(c, FormCliente.this);
            task.execute();
            try {
                Thread.sleep(3500);
                finish();
            }catch (Exception e){
                Log.e("Adapter", "Erro: "+e.getMessage());

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
        String NomeHolder = editTextNameCl.getText().toString();
        //System.out.println(NomeHolder);
        String EmailHolder = editTextEmailCl.getText().toString();
        //System.out.println(EmailHolder);
        String CpfHolder = editTextCpf.getText().toString();
        //System.out.println(CpfHolder);
        String PhoneHolder = editTextPhoneCl.getText().toString();
        //System.out.println(PhoneHolder);
        String EstadoHolder = editTextUfCl.getText().toString();
        //System.out.println(EstadoHolder);
        String CidadeHolder = editTextCityCl.getText().toString();
        //System.out.println(CidadeHolder);
        String EnderecoHolder = editTextAddressCl.getText().toString();
        //System.out.println(EnderecoHolder);
        String CepHolder = editTextPostalCl.getText().toString();
        //System.out.println(CepHolder);
        String PaisHolder = editTextCountryCl.getText().toString();
        //System.out.println(PaisHolder);
        String TipoResiduoHolder = editTextResiduoCl.getText().toString();
        //System.out.println(TipoResiduoHolder);
        if (Cvidro.isChecked()){
            TipoReciclavelCliente = "Vidro";
        } else if (Cplastico.isChecked()) {
            TipoReciclavelCliente = "Plástico";
        } else if (Cmetal.isChecked()) {
            TipoReciclavelCliente = "Metal";
        } else if (Cpapel.isChecked()) {
            TipoReciclavelCliente = "Papel";
        }else if(Coutros.isChecked()){
            TipoReciclavelCliente = "Outros";
        }else {
            TipoReciclavelCliente = "";
        }


        if (TextUtils.isEmpty(NomeHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(CpfHolder) || TextUtils.isEmpty(PhoneHolder) || TextUtils.isEmpty(EstadoHolder) || TextUtils.isEmpty(CidadeHolder) || TextUtils.isEmpty(EnderecoHolder) || TextUtils.isEmpty(CepHolder) || TextUtils.isEmpty(PaisHolder) || TextUtils.isEmpty(TipoResiduoHolder) || TextUtils.isEmpty(TipoReciclavelCliente)){
            EditTextEmptyHolder = false;

        } else{
            EditTextEmptyHolder = true;

        }

    }
    // Verificando se e-mail já existe ou não.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Abrindo SQLite database com permissoes de escrita.
        sqLiteDatabaseObj = database.getReadableDatabase();
        EmailHolder = editTextEmailCl.getText().toString();
        //System.out.println(EmailHolder);
        // Adicionando query de consulta de email para o "cursor".
       cursor = sqLiteDatabaseObj.query("Cliente",null, ""+"email"+"=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();
                idpk = cursor.getInt(cursor.getColumnIndexOrThrow("idpk"));
                //System.out.println(idpk);

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
        editTextCpf.getText().clear();
        editTextPhoneCl.getText().clear();
        editTextUfCl.getText().clear();
        editTextCityCl.getText().clear();
        editTextAddressCl.getText().clear();
        editTextPostalCl.getText().clear();
        editTextCountryCl.getText().clear();
        editTextResiduoCl.getText().clear();
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        //EmptyEditTexBeforeBack();
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(FormCliente.this);
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