package com.pucpr.greencycle.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.greencycle.R;
import com.pucpr.greencycle.controller.FormColetasAdapter;
import com.pucpr.greencycle.model.ContactDatabase;
import com.pucpr.greencycle.model.DataModel;
import com.pucpr.greencycle.model.Request;
import com.pucpr.greencycle.util.AlterarSolicitacaoAsyncTask;

import java.util.ArrayList;
import java.util.Calendar;

public class FormColetas extends AppCompatActivity {
    FormColetasAdapter adapter = new FormColetasAdapter();
    Cursor cursor;

    String idloginclient, NameClient, EmailClient, idlogincompany, NameCompany, EmailCompany, ContactCompany, Date, Hour, CnpjClient, PhoneClient, EstadoClient,
            CidadeClient, EnderecoClient, CepClient, PaisClient, TipoResiduoClient, DescResiduoClient, EmailHolder,EmailHolderClient;
    String F_Result = "Não Encontrado", F_Result_Company = "Usuário não Encontrado";

    int IndexCompany, IdRequest, IndexClient,index, idpk;
    RecyclerView recyclerViewColetas;
    int singleitem_selection_position = -1;
    ContactDatabase database;
    SQLiteDatabase sqLiteDatabaseObj;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime,etPedidoColeta;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_formcoletas);
        setTitle("Activity Retirada de Coletas");
        recyclerViewColetas = findViewById(R.id.recyclerViewColetas);
        btnDatePicker = (Button) findViewById(R.id.btnDatePiker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        etPedidoColeta = findViewById(R.id.etPedidoColeta);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnDatePicker) {
                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(FormColetas.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnTimePicker) {

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(FormColetas.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txtTime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }

            }
        });

        Bundle extras = getIntent().getExtras();
        IndexCompany = (extras.getInt("EXTRA_ID"));
        //System.out.println(IndexCompany);
        idlogincompany = extras.getString("EXTRA_USERID");
        String Name = extras.getString("EXTRA_NAME");
        EmailHolder = extras.getString("EXTRA_EMAIL");

        database = new ContactDatabase(this);
        DataModel.getInstance().createRequestDatabase(FormColetas.this, EmailHolder);

        System.out.println(EmailHolder);
        //ArrayList<Request> requestcp = database.getRequestsCpFromDB(EmailHolder);
        //for (Request c:requestcp) {
         //   c.print();
        //}
        ArrayList<Request> requests = database.getRequestsFromDB();
        for (Request c:requests) {
            c.print();
        }

        //database.removeRequestInDB(requests.get(0));
        //database.removeRequestInDB(requests.get(1));
        //database.removeRequestInDB(requests.get(2));
        //database.removeRequestInDB(requests.get(3));
        //database.removeRequestInDB(requests.get(4));
        //database.removeRequestInDB(requests.get(5));
        //database.removeRequestInDB(requests.get(6));
        //database.removeRequestInDB(requests.get(7));
        //database.removeRequestInDB(requests.get(8));
        //database.removeRequestInDB(requests.get(9));
        //database.removeRequestInDB(requests.get(10));
        //database.removeRequestInDB(requests.get(11));
        //database.removeRequestInDB(requests.get(12));
        //database.removeRequestInDB(requests.get(13));
        //database.removeRequestInDB(requests.get(14));
        //database.removeRequestInDB(requests.get(15));
        //database.removeRequestInDB(requests.get(16));
        //database.removeRequestInDB(requests.get(17));
        //database.removeRequestInDB(requests.get(18));




        recyclerViewColetas.setAdapter(adapter);
        recyclerViewColetas.setLayoutManager(
                new LinearLayoutManager(FormColetas.this)
        );
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(
                FormColetas.this,DividerItemDecoration.VERTICAL
        );
        recyclerViewColetas.addItemDecoration(itemDecoration);

        adapter.setOnItemClickListener(new FormColetasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setSingleSelection(position);
                index = position;
                System.out.println(index);
            }
        });
    }
    private void setSingleSelection(int adapterPosition){
        if (adapterPosition == RecyclerView.NO_POSITION) {
            return;
        }
        adapter.notifyItemChanged(singleitem_selection_position);
        singleitem_selection_position = adapterPosition;
        adapter.notifyItemChanged(singleitem_selection_position);
    }
    public void agendaRetirada(View v){
        if (adapter.getSelected()!= null){
            CheckingDataRequest();
            EmptyEditTextAfterDataInsert();
        }else {
            Toast.makeText(this, "Selecione um cliente.", Toast.LENGTH_SHORT).show();
        }
    }

    public void CheckingDataRequest(){
        if (adapter.getSelected() != null){
            //index = adapter.getSelected();
            System.out.println(adapter.getSelected());
            // Abrindo SQLite database com permissoes de leitura.
            sqLiteDatabaseObj = database.getReadableDatabase();
            System.out.println(EmailHolder);
            // Adicionando query de consulta de email para o "cursor".
            //cursor = sqLiteDatabaseObj.query("Solicitacao",null, "" + "company_email" + "=?"+  " and "+ "client_email" + "=?" + " and "+ "date" + "=?",new String[]{EmailHolder, String.valueOf(adapter.getSelected()),"null"}, null, null, null);
            cursor = sqLiteDatabaseObj.query("Solicitacao",null, "" + "id" + "=?" + " and "+ "company_email" + "=?" + " and "+ "date" + "=?",new String[]{String.valueOf(adapter.getSelected()),EmailHolder,"1900-01-01"}, null, null, null);
            while (cursor.moveToNext()){
                if (cursor.isFirst()){
                    cursor.moveToFirst();

                    // Storing data associated with entered id Solicitacao table.
                    IdRequest = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                    idpk = cursor.getInt(cursor.getColumnIndexOrThrow("idpk"));
                    System.out.println(idpk);
                    idloginclient = cursor.getString(cursor.getColumnIndexOrThrow("client_idlogin"));
                    NameClient = cursor.getString(cursor.getColumnIndexOrThrow("client_name"));
                    EmailClient = cursor.getString(cursor.getColumnIndexOrThrow("client_email"));
                    //System.out.println(EmailClient);
                    NameCompany = cursor.getString(cursor.getColumnIndexOrThrow("company_name"));
                    EmailCompany = cursor.getString(cursor.getColumnIndexOrThrow("company_email"));
                    System.out.println(EmailCompany);
                    ContactCompany = cursor.getString(cursor.getColumnIndexOrThrow("company_contact"));
                    System.out.println(ContactCompany);
                    Date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    System.out.println(Date);
                    Hour = cursor.getString(cursor.getColumnIndexOrThrow("hour"));
                    System.out.println(Hour);
                    TipoResiduoClient = cursor.getString(cursor.getColumnIndexOrThrow("residuo"));
                    DescResiduoClient = cursor.getString(cursor.getColumnIndexOrThrow("descresiduo"));
                    //System.out.println(DescResiduoClient);
                    F_Result = "Agendamento Encontrado";

                    //Fechando cursor.
                    //cursor.close();
                }
            }
            CheckFinalResult();
        }
    }
    public void updateData(){
        String CompanyContact = etPedidoColeta.getText().toString();
        String CompanyDate = txtDate.getText().toString();
        System.out.println(CompanyDate);
        String CompanyHour = txtTime.getText().toString();
        if (CompanyContact.length() > 1 && CompanyDate.length() > 1 && CompanyHour.length() > 1) {
        Request r = DataModel.getInstance().getRequestCompany(index);
        r.setIdpk(idpk);
        r.getClient_idlogin();
        r.getClient_name();
        r.getClient_email();
        r.getCompany_idlogin();
        r.getCompany_name();
        r.getCompany_email();
        r.setCompany_contact(CompanyContact);
        r.setDate(CompanyDate);
        r.setHour(CompanyHour);
        r.getResiduo();
        r.getDescresiduo();
            AlertDialog.Builder builder = new AlertDialog.Builder( FormColetas.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.confirm_alert_msg_client);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DataModel.getInstance().updateRequest(r, index);
                    Toast.makeText(FormColetas.this, "Agendamento com "+NameClient+" cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    AlterarSolicitacaoAsyncTask task = new AlterarSolicitacaoAsyncTask(r,FormColetas.this);
                    task.execute();
                    //finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();
            //finish();
        }else{
            Toast.makeText(this, "Preencha todos os campos para realizar o agendamento.", Toast.LENGTH_SHORT).show();
            //finish();
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder( FormColetas.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.empty_request_alert_msg);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.create().show();*/

        }
    }
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if (F_Result.equalsIgnoreCase("Agendamento Encontrado")){
            // If date already doesn't exists then schedule details will entered to SQLite database.
            updateData();

        }else{
            // If date is exists then toast msg will display.
            Toast.makeText(FormColetas.this,"Agendamento já cadastrado para este cliente",Toast.LENGTH_SHORT).show();
            //finish();
        }
        //F_Result = "Não Encontrado";
    }
    public void EmptyEditTextAfterDataInsert(){
        F_Result ="";
    }



}
