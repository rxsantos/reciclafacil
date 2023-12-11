package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.model.Contact;
import com.pucpr.reciclafacil.model.ContactDatabase;
import com.pucpr.reciclafacil.model.DataModel;
import com.pucpr.reciclafacil.util.IncluirUserAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registro extends AppCompatActivity {

    EditText Name, Email, Password;
    Button Cadastro;
    RadioButton Coleta, Descarte;
    String NameHolder, EmailHolder, PasswordHolder, Op;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    ContactDatabase database;
    Cursor cursor;
    String F_Result = "Usuário não Encontrado";
    //String datainc;
    Date dataAtual = Calendar.getInstance().getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = format.format(dataAtual);
    private Contact Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Activity Registro");

        DataModel.getInstance().createDatabase(Registro.this);
        database = new ContactDatabase(this);

        Name = findViewById(R.id.fullnameEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwdEditText);
        Cadastro = findViewById(R.id.registrarButton);
        Coleta = findViewById(R.id.coleta);
        Descarte  = findViewById(R.id.descarte);





        Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    //Checar EditText se vazio ou nao
                    CheckEditTextStatus();

                    //Metodo para checar se Email ja existe ou nao
                    CheckingEmailAlreadyExistsOrNot();

                    //Verificar se EditText esta vazio apos o processo de insercao
                    EmptyEditTextAfterDataInsert();

            }
        });

    }




    //Método para inserir dados no Banco de Dados SQLite
    public void InsertDataIntoSQLiteDatabase() {
        if (EditTextEmptyHolder) {
            //Query SQLite para inserir dados nas tabelas
            Contact c = new Contact();
            c.setName(NameHolder);
            c.setEmail(EmailHolder);
            c.setPassword(PasswordHolder);
            c.setOp(Op);
            c.setDatainc(date);
            DataModel.getInstance().addContact(c);



            //Printando mensagem toast depois de completar o inserte
            Toast.makeText(Registro.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            IncluirUserAsyncTask task = new IncluirUserAsyncTask( c, Registro.this );
            task.execute();
            //finish();
        }
        // Este bloco será executado se algum EditText de registro estiver em branco.
        else {

            //Printando mensagem toast se  algum EditText de registro estiver em branco.
            Toast.makeText(Registro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        }
    }

    //Limpa editText depois de finalizar o processo do metodo inserte.
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    //Metodo para checar se EditText está vazio ou não.
    public void CheckEditTextStatus(){

        //Obtendo valor de todos EditText e armazenando em variáveis de String.
        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        if (Coleta.isChecked()){
            Op = "Empresa";
        }else if(Descarte.isChecked()){
            Op = "Cliente";
        }

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(Op)){
            EditTextEmptyHolder = false;
        } else{
            EditTextEmptyHolder = true;
        }

    }
        // Verificando se e-mail já existe ou não.
    public void CheckingEmailAlreadyExistsOrNot(){
            // Abrindo SQLite database com permissoes de escrita.
        sqLiteDatabaseObj = database.getWritableDatabase();

            // Adicionando query de consulta de email para o "cursor".
        cursor = sqLiteDatabaseObj.query("Contact",null,"" + "email" + "=?",new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()){
                if (cursor.isFirst()){
                    cursor.moveToFirst();

                    // Se o email já existir então a variável Result será setada com o valor "Email Encontrado".
                    F_Result = "Email Encontrado";

                    //Fechando cursor.
                    cursor.close();
                }
            }
            CheckFinalResult();
        }


        public void CheckFinalResult(){
            // Verificando se email já existe ou não.
        if (F_Result.equalsIgnoreCase("Email Encontrado")){

            // Se email já existir então mensagem Toast será mostrada.
            Toast.makeText(Registro.this,"Email já foi utilizado. Tente outro email!",Toast.LENGTH_LONG).show();
        }else{
            // Se o e-mail não existir, as informacoes de registro do usuário serão inseridas no SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
            F_Result = "Não Encontrado";
        }

}



