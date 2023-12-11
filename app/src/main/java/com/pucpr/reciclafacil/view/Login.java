package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pucpr.reciclafacil.R;

import com.pucpr.reciclafacil.model.ContactDatabase;
import com.pucpr.reciclafacil.util.SincronizarCliente;
import com.pucpr.reciclafacil.util.SincronizarContact;
import com.pucpr.reciclafacil.util.SincronizarEmpresa;
import com.pucpr.reciclafacil.util.SincronizarSolicitacao;

public class Login extends AppCompatActivity {

    Button Login, Cadastrar;
    EditText Email, Password;
    String EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    String TempPassword = "Usuário não Encontrado", op = "Admin", Name, Ident;
    public static final String UserEmail = "";
    public static final String UserName = "";
    public static final String UserId = "";


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Activity Login");
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Login = findViewById(R.id.loginButton);
        Cadastrar = findViewById(R.id.cadastrarButton);


                SincronizarContact task = new SincronizarContact(Login.this);
                task.execute();

                SincronizarCliente taskc = new SincronizarCliente(Login.this);
                taskc.execute();

                SincronizarEmpresa taske = new SincronizarEmpresa(Login.this);
                taske.execute();

                SincronizarSolicitacao tasks = new SincronizarSolicitacao(Login.this);
                tasks.execute();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Carregando informações do sistema, aguarde ...", Snackbar.LENGTH_SHORT)
                  //      .setAction("Action", null).show();

                //TODO: Criar Classes para Sincronizar os dados do sistema - AsyncTask
                //SincronizarSistema task = new SincronizarSistema();
                //task.execute();
                SincronizarContact task = new SincronizarContact(Login.this);
                task.execute();

                SincronizarCliente taskc = new SincronizarCliente(Login.this);
                taskc.execute();

                SincronizarEmpresa taske = new SincronizarEmpresa(Login.this);
                taske.execute();

                SincronizarSolicitacao tasks = new SincronizarSolicitacao(Login.this);
                tasks.execute();


            }
        });

        database = new ContactDatabase(this);

        //database.createContactInDB(new ContactLogin("Roberto Xavier","roberto@gmail.com", "1234", "Admin"));
        //database.insertContactInDB(new com.pucpr.greencycle.model.ContactLogin("Administrador", "admin@admin.com", "admin", "Admin", String.valueOf(date)));


        /*ArrayList<com.pucpr.greencycle.model.ContactLogin>contacts = database.getContactsFromDB();
        for (com.pucpr.greencycle.model.ContactLogin c:contacts) {
            c.print();
        }*/
        //contacts.get(6).setOp("Empresa");
        //database.updateContactInDB(contacts.get(6));
        //database.removeContactInDB(contacts.get(4));
        //database.removeContactInDB(contacts.get(1));
        //database.removeContactInDB(contacts.get(2));
        //database.removeContactInDB(contacts.get(4));
        //database.removeContactInDB(contacts.get(5));
        //database.removeContactInDB(contacts.get(0));
        //database.removeContactInDB(contacts.get(0));
        //contacts.get(53).setName("Roberto Santos");



    }
    public void loginButtonOnClick(View v){

        //TODO: Criar Classe SincronizarSistema AsyncTask
        //SincronizarSistema task = new SincronizarSistema();
        //task.execute();


        // Calling EditText is empty or no method.
        CheckEditTextStatus();

        // Calling login method.
        LoginFunction();

    }


    public void registerButtonOnClick(View v){
        //TODO: Criar Classe SincronizarSistema AsyncTask
        //SincronizarSistema task = new SincronizarSistema();
        //task.execute();

        Intent intent = new Intent(Login.this, Registro.class);
        startActivity(intent);
    }
    public void recoverButtonOnClick(View v){
        Intent intent = new Intent(Login.this, RecuperarSenha.class);
        startActivity(intent);
    }

    // Login function starts from here.
    public void LoginFunction(){
        if(EditTextEmptyHolder) {
            // Opening SQLite database write permission.
            sqLiteDatabaseObj = database.getWritableDatabase();

            // Adding search email query to cursor.
            Cursor cursor = sqLiteDatabaseObj.query("Contact", null, " " + "email" + "=?", new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                    op = cursor.getString(cursor.getColumnIndexOrThrow("op"));
                    Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Ident = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    //System.out.println(Ident);


                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();
        }
        else {
            //If any of login EditText empty then this block will be executed.
            Toast.makeText(Login.this,"Digite o nome de usuário ou a senha.",Toast.LENGTH_LONG).show();
        }
    }
    // Checking EditText is empty or not.

    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
            //Toast.makeText(Login.this,"Login Fail",Toast.LENGTH_LONG).show();
        }
        else {
            EditTextEmptyHolder = true ;
            //Toast.makeText(Login.this,"Login bem sucedido!",Toast.LENGTH_LONG).show();
        }
    }
    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            if(op.equals("Cliente")){
                Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_SHORT).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, ApresentacaoCliente.class);
                // Sending Email to Dashboard Activity using intent.
                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME",Name);
                extras.putString("EXTRA_EMAIL",EmailHolder);
                extras.putString("EXTRA_USERID",Ident);
                intent.putExtras(extras);
                startActivity(intent);

            }else if(op.equals("Empresa")){
                Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_SHORT).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, ApresentacaoEmpresa.class);
                // Sending Email to Dashboard Activity using intent.
                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME",Name);
                extras.putString("EXTRA_EMAIL",EmailHolder);
                extras.putString("EXTRA_USERID",Ident);
                intent.putExtras(extras);
                startActivity(intent);
            }else{
                //Toast.makeText(Login.this,"Login bem Sucedido!",Toast.LENGTH_LONG).show();
                // Going to Dashboard activity after login success message.
                Intent intent = new Intent(Login.this, ContactLogin.class);
                // Sending Email to Dashboard Activity using intent.
                intent.putExtra(UserEmail, EmailHolder);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(Login.this,"Nome de usuário ou senha estão incorretos, tente novamente!",Toast.LENGTH_SHORT).show();
            System.out.println(TempPassword);
        }
        TempPassword = "NOT_FOUND" ;
    }
    /*
    //TODO: Criar Classe SincronizarSistema AsyncTask
    //Sincronizar Sistema
    private class SincronizarSistema extends AsyncTask<String, String, String>{
        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;


        public SincronizarSistema(){
            this.builder = new Uri.Builder();
            builder.appendQueryParameter("app", "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");


        }
        @Override
        protected void onPreExecute(){
            Log.i("WebService","SincronizarSistema()");
            progressDialog.setMessage("Carregando as informações, aguarde...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            // Montar a URL com o endereço do scrip PHP
            try {
                url = new URL(UtilApp.URL_WEB_SERVICE+"APISincronizarContact.php");

            }catch (MalformedURLException e){
                Log.e("WebService","MalformedURLException - "+ e.getMessage());

            }catch (Exception erro){
                Log.e("WebService","Exception - "+ erro.getMessage());
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(UtilApp.CONNECTION_TIMEOUT);
                conn.setReadTimeout(UtilApp.READ_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset","utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);


                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();

            }catch (IOException e){
                Log.e("WebService","IOException - "+ e.getMessage());

            }
            //TODO: Recebe JSON de resposta do WebService
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK){
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String linha;
                    while ((linha = reader.readLine()) != null){
                        result.append(linha);
                    }
                    return (result.toString());

                } else {
                    return "Erro de conexão";
                }

            }catch (IOException e){
                Log.e("WebService","IOException - "+ e.getMessage());
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }
        @Override
        protected void onPostExecute(String result){

            try {
                JSONArray jsonArray = new JSONArray(result);
                if (jsonArray.length() != 0){
                    //Salvar os dados no banco de dados SQLite
                    DataModel.getInstance().dlTableContact();
                    DataModel.getInstance().crTableContact(Login.this);
                    //DataModel.getInstance().crTables();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //System.out.println(jsonObject);

                        Contact c = new Contact();
                        //c.setId(jsonObject.getInt(String.valueOf("id")));
                        c.setIdpk(jsonObject.getInt(String.valueOf("id")));
                        c.setName(jsonObject.getString("name"));
                        c.setEmail(jsonObject.getString("email"));
                        c.setPassword(jsonObject.getString("password"));
                        c.setOp(jsonObject.getString("op"));
                        //System.out.println(c);
                        //c.setDatainc(jsonArray.getInt("datainc"));

                        DataModel.getInstance().addContact(c);


                        //Client cl = new Client();
                        //cl.setIdpk(jsonObject.getInt(String.valueOf("id")));
                        //cl.set
                        //DataModel.getInstance().addClient(cl);

                    }
                } else{
                    UtilApp.showMessage(context,"Nenhum regitro encontrado no momento...");
                }

            }catch (JSONException e){
                Log.e("WebService", "Erro JSONException - "+e.getMessage());

            } finally {
                if (progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }
        }*/
    }


