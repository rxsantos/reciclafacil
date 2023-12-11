package com.pucpr.reciclafacil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.controller.AppController;
import com.pucpr.reciclafacil.model.ContactDatabase;
import com.pucpr.reciclafacil.model.DataModel;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    ImageView imageView;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setTitle("Activity Splash");
        imageView = findViewById(R.id.imageView);




        database = new ContactDatabase(this);
        sqLiteDatabase = database.getWritableDatabase();
        //DataModel.getInstance().crTables(SplashScreen.this);
        DataModel.getInstance().createDatabase(SplashScreen.this);
        DataModel.getInstance().createClientDatabase(SplashScreen.this);
        DataModel.getInstance().createCompanyDatabase(SplashScreen.this);
        DataModel.getInstance().createRequestDatabase(SplashScreen.this, "");


        Glide.with(this).load(R.drawable.recyclegreen).into(imageView);

        if(AppController.verifyGooglePlayServices(SplashScreen.this)){
            new Handler().postDelayed(new Runnable() {
                /*
                 * Exibindo splash com um timer.
                 */
                @Override
                public void run() {
                    // Esse método será executado sempre que o timer acabar
                    // E inicia a activity principal
                    Intent i = new Intent(SplashScreen.this,
                            Login.class);
                    startActivity(i);

                    // Fecha esta activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            Toast.makeText(this, "Google Play Services não configurado...", Toast.LENGTH_SHORT).show();
        }

    }


}