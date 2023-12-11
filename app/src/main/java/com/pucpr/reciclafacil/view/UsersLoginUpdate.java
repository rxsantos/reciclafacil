package com.pucpr.reciclafacil.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pucpr.reciclafacil.R;
import com.pucpr.reciclafacil.model.Contact;
import com.pucpr.reciclafacil.model.DataModel;
import com.pucpr.reciclafacil.util.AlterarUserAsyncTask;


public class UsersLoginUpdate extends AppCompatActivity {

    EditText editNameUser, editEmailUser, editPasswordUser;

    RadioButton Cliente, Empresa, Admin;

    int index;
    long idpk;
    String Op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_users_admin);
        setTitle("Activity Adicionar Usuários");

        DataModel.getInstance().createDatabase(UsersLoginUpdate.this);

        editNameUser = findViewById(R.id.editNameUpdate);
        editEmailUser = findViewById(R.id.editEmailUpdate);
        editPasswordUser = findViewById(R.id.editPasswordUpdate);
        Cliente = findViewById(R.id.radioButtonClienteUp);
        Empresa  = findViewById(R.id.radioButtonEmpresaUp);
        Admin  = findViewById(R.id.radioButtonAdminUp);

        Bundle extra = getIntent().getExtras();
        index = extra.getInt("index");
        if (index != -1){
            Contact c = DataModel.getInstance().getContact(index);
            idpk = c.getIdpk();
            editNameUser.setText(c.getName());
            editEmailUser.setText(c.getEmail());
            editPasswordUser.setText(c.getPassword());

        }

    }
    @SuppressLint("MissingSuperCall")
    public void onBackPressed(){
        String Nome = editNameUser.getText().toString();
        String Email = editEmailUser.getText().toString();
        String Password = editPasswordUser.getText().toString();

        if (Admin.isChecked()){
            Op = "Admin";
        }else if(Empresa.isChecked()){
            Op = "Empresa";
        } else if(Cliente.isChecked()){
            Op = "Cliente";
        }else{
            Op="";
        }
        if (Nome.length() > 1 && Email.length() > 1 && Password.length() > 1 && Op.length() > 1) {

                Contact c = DataModel.getInstance().getContact(index);
                c.setIdpk(idpk);
                c.setName(Nome);
                c.setEmail(Email);
                c.setPassword(Password);
                c.setOp(Op);
                try {
                    DataModel.getInstance().updateContact(c, index);

                    AlterarUserAsyncTask task =
                            new AlterarUserAsyncTask(c,UsersLoginUpdate.this);
                    task.execute();
                    Toast.makeText(this, "Usuário Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    //Thread.sleep(3000);
                    //finish();
                }catch (Exception e){
                    Log.e("Adapter", "Erro: "+e.getMessage());
                }


        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder( UsersLoginUpdate.this);
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

        }
    }


}