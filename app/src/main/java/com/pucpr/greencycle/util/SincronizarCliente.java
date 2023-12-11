package com.pucpr.greencycle.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.pucpr.greencycle.model.Client;
import com.pucpr.greencycle.model.Contact;
import com.pucpr.greencycle.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SincronizarCliente extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;
    HttpURLConnection conn;
    URL url = null;
    Uri.Builder builder;
    Context context;


    public SincronizarCliente(Context context){
        this.builder = new Uri.Builder();
        this.context = context;

        builder.appendQueryParameter("app", "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");

    }
    @Override
    protected void onPreExecute(){
        Log.i("WebService","SincronizarClient()");
        //progressDialog = new ProgressDialog(context);
        //progressDialog.setMessage("Carregando informações do sistema, aguarde...");
        //progressDialog.setCancelable(false);
        //progressDialog.show();

    }
    @Override
    protected String doInBackground(String... strings) {
        // Montar a URL com o endereço do scrip PHP
        try {
            url = new URL(UtilApp.URL_WEB_SERVICE+"APISincronizarCliente.php");

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
                DataModel.getInstance().dlTableClient();
                DataModel.getInstance().crTableClient(context);
                //DataModel.getInstance().crTables();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //System.out.println(jsonObject);

                    Client c = new Client();
                    //c.setId(jsonObject.getInt(String.valueOf("id")));
                    c.setIdpk(jsonObject.getInt(String.valueOf("id")));
                    c.setIdlogin(jsonObject.getString("client_idlogin"));
                    c.setName(jsonObject.getString("name"));
                    c.setEmail(jsonObject.getString("email"));
                    c.setCpf(jsonObject.getString("cpf"));
                    c.setPhone(jsonObject.getString("phone"));
                    c.setState(jsonObject.getString("state"));
                    c.setCity(jsonObject.getString("city"));
                    c.setAddress(jsonObject.getString("address"));
                    c.setZipcode(jsonObject.getString("zipcode"));
                    c.setCountry(jsonObject.getString("country"));
                    c.setResiduo(jsonObject.getString("residuo"));
                    c.setDescresiduo(jsonObject.getString("descresiduo"));
                    //System.out.println(c);
                    //c.setDatainc(jsonArray.getInt("datainc"));

                    DataModel.getInstance().addClient(c);


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
    }
}

