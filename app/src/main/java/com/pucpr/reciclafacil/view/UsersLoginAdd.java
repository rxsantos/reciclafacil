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


public class UsersLoginAdd extends AppCompatActivity {

    EditText editNameUser, editEmailUser, editPasswordUser;
    Button Cadastrar;
    RadioButton Cliente, Empresa, Admin;

    int index;
    EditText Name, Email, Password;
    Button Cadastro;
    //RadioButton Coleta, Descarte;
    String NameHolder, EmailHolder, PasswordHolder, Op, datainc;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    ContactDatabase database;
    Cursor cursor;
    String F_Result = "Usuário não Encontrado";
    Date dataAtual = Calendar.getInstance().getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    String date = format.format(dataAtual);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users_admin);
        setTitle("Activity Adicionar Usuários");

        DataModel.getInstance().createDatabase(UsersLoginAdd.this);
        database = new ContactDatabase(this);

        Cadastrar = (Button) findViewById(R.id.userAddButton);
        editNameUser = findViewById(R.id.editNameUser);
        editEmailUser = findViewById(R.id.editEmailUser);
        editPasswordUser = findViewById(R.id.editPasswordUser);
        Cliente = (RadioButton) findViewById(R.id.radioButtonCliente);
        Empresa  = (RadioButton) findViewById(R.id.radioButtonEmpresa);
        Admin  = (RadioButton) findViewById(R.id.radioButtonAdmin);


        Cadastrar.setOnClickListener(new View.OnClickListener() {
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



    //Insert data into SQLite database method
    public void InsertDataIntoSQLiteDatabase() {
        if (EditTextEmptyHolder) {
            //SQLite query to insert data into table
            Contact c = new Contact();
            c.setName(NameHolder);
            c.setEmail(EmailHolder);
            c.setPassword(PasswordHolder);
            c.setOp(Op);
            c.setDatainc(date);
            DataModel.getInstance().addContact(c);
            //DataModel.getInstance().addContact(new Contact(NameHolder, EmailHolder, PasswordHolder, Op, date));


            //Printing toast message after done insert
            Toast.makeText(UsersLoginAdd.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
            IncluirUserAsyncTask task = new IncluirUserAsyncTask( c, UsersLoginAdd.this );
            task.execute();
            //finish();
        }
        // This block will execute if any of the registration EditText is empty.
        else {
            //Printing toast message if any of EditTExt is Empty
            Toast.makeText(UsersLoginAdd.this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        }

    }
    //Empty editText after done inserting process method
    public void EmptyEditTextAfterDataInsert(){
        editNameUser.getText().clear();
        editEmailUser.getText().clear();
        editPasswordUser.getText().clear();
    }

    //Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        //Getting value from All EditText and storing into String Variables.
        NameHolder = editNameUser.getText().toString();
        EmailHolder = editEmailUser.getText().toString();
        PasswordHolder = editPasswordUser.getText().toString();
        if (Admin.isChecked()){
            Op = "Admin";
        }else if(Empresa.isChecked()){
            Op = "Empresa";
        } else {
            Op = "Cliente";
        }

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(Op)){
            EditTextEmptyHolder = false;
        } else{
            EditTextEmptyHolder = true;
        }
    }
    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = database.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query("Contact",null,"" + "email" + "=?",new String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()){
            if (cursor.isFirst()){
                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Encontrado";

                //Closing cursor.
                cursor.close();
            }
        }
        CheckFinalResult();
    }


    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if (F_Result.equalsIgnoreCase("Email Encontrado")){

            // If email is exists then toast msg will display.
            Toast.makeText(UsersLoginAdd.this,"Email já foi utilizado. Tente outro email!",Toast.LENGTH_LONG).show();
        }else{
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
            //userButtonOnClick();
        }
        F_Result = "Não Encontrado";
    }

}