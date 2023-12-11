package com.pucpr.greencycle.model;

import android.util.Log;

public class Contact {
    private long id; // PK SQLite
    private long idpk; //PK MySQL remoto

    private String  name, email, password, op, datainc;


    public Contact(long id, long idpk, String name, String email, String password, String op, String datainc) {
        this.id = id;
        this.idpk = idpk;
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
        this.datainc = datainc;
    }

    public Contact(long id, String name, String email, String password, String op, String datainc) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
        this.datainc = datainc;
    }

    public Contact(String name, String email, String password, String op, String datainc) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.op = op;
        this.datainc = datainc;
    }

    public Contact() {

    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdpk() {
        return idpk;
    }

    public void setIdpk(long idpk) {
        this.idpk = idpk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }


    public String getDatainc() {
        return datainc;
    }

    public void setDatainc(String datainc) {
        this.datainc = datainc;
    }


    public void print(){
        Log.v("SQLDatabase", "Contact["+id+"]: "+name+" Idpk: "+idpk+ " Email: "+email+ " Password: "+password+ " Tipo: " +op);
    }

}
