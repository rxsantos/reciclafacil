package com.pucpr.reciclafacil.util;

import android.content.Context;
import android.widget.Toast;

public class UtilApp {
    public static void showMessage (Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static final String URL_WEB_SERVICE = "https://webservice.reciclafacil.org/reciclafacil/webserviceandroid/";
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

}
