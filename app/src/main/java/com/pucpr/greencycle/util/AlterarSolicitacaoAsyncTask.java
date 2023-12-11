package com.pucpr.greencycle.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.pucpr.greencycle.model.Company;
import com.pucpr.greencycle.model.Request;

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

public class AlterarSolicitacaoAsyncTask extends AsyncTask<String, String, String> {

    ProgressDialog progressDialog;

    HttpURLConnection conn;
    URL url = null;
    Uri.Builder builder;

    //private MediaEscolarController controller;

    Context context;


    public AlterarSolicitacaoAsyncTask(Request obj, Context context){
        this.builder = new Uri.Builder();

        this.context = context;

        //Contact c = new Contact();

        // Todo: Passagem de parâmetros para o WebService
        builder.appendQueryParameter("app", "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");

        //Todo: Usar Data Model
        builder.appendQueryParameter("idpk", String.valueOf(obj.getIdpk()));
        builder.appendQueryParameter("client_id", obj.getClient_idlogin());
        builder.appendQueryParameter("client_name", obj.getClient_name());
        builder.appendQueryParameter("client_email", obj.getClient_email());
        builder.appendQueryParameter("company_id", obj.getCompany_idlogin());
        builder.appendQueryParameter("company_name", obj.getCompany_name());
        builder.appendQueryParameter("company_email", obj.getCompany_email());
        builder.appendQueryParameter("company_contact", obj.getCompany_contact());
        builder.appendQueryParameter("date", obj.getDate());
        builder.appendQueryParameter("hour", obj.getHour());
        builder.appendQueryParameter("residuo", obj.getResiduo());
        builder.appendQueryParameter("descresiduo", obj.getDescresiduo());
        builder.appendQueryParameter("datainc", obj.getDatainc());
        //builder.appendQueryParameter(obj.getDatainc(), obj.getDatainc());

    }

    @Override
    protected void onPreExecute() {
        Log.i("WebService", "AlterarSolicitacaoAsyncTask()");

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Salvando informações, por favor aguarde...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            url = new URL(UtilApp.URL_WEB_SERVICE + "APIAlterarSolicitacao.php");

        } catch (MalformedURLException e) {

            Log.e("WebService", "MalformedURLException - " + e.getMessage());

        } catch (Exception e) {

            Log.e("WebService", "Exception - " + e.getMessage());

        }

        try {

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(UtilApp.READ_TIMEOUT);
            conn.setConnectTimeout(UtilApp.CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("charset", "utf-8");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.connect();

            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException erro) {

            Log.e("WebService", "IOException - " + erro.getMessage());

        }

        try {

            int response_code = conn.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return (result.toString());

            } else {
                return ("Erro de Conexão");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            conn.disconnect();
        }

    }

    @Override
    protected void onPostExecute(String result) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }
}
