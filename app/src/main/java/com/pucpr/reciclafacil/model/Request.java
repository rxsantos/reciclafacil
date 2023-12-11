package com.pucpr.reciclafacil.model;

import android.util.Log;

public class Request {
    private long id,idpk;
    private String client_idlogin, client_name, client_email, company_idlogin, company_name, company_email, company_contact, date, hour,residuo, descresiduo,datainc;
    //private String client_idlogin, client_name, client_email, company_idlogin, company_name, company_email, company_contact, date, hour, cnpj, cpf, phone, state,  city, address, zipcode, country, residuo, descresiduo, region;


    public Request(long id, long idpk, String client_idlogin, String client_name, String client_email, String company_idlogin, String company_name, String company_email, String company_contact, String date, String hour, String residuo, String descresiduo, String datainc) {
        this.id = id;
        this.idpk = idpk;
        this.client_idlogin = client_idlogin;
        this.client_name = client_name;
        this.client_email = client_email;
        this.company_idlogin = company_idlogin;
        this.company_name = company_name;
        this.company_email = company_email;
        this.company_contact = company_contact;
        this.date = date;
        this.hour = hour;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }

    public Request(long idpk, String client_idlogin, String client_name, String client_email, String company_idlogin, String company_name, String company_email, String company_contact, String date, String hour, String residuo, String descresiduo, String datainc) {
        this.idpk = idpk;
        this.client_idlogin = client_idlogin;
        this.client_name = client_name;
        this.client_email = client_email;
        this.company_idlogin = company_idlogin;
        this.company_name = company_name;
        this.company_email = company_email;
        this.company_contact = company_contact;
        this.date = date;
        this.hour = hour;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }

    public Request(String client_idlogin, String client_name, String client_email, String company_idlogin, String company_name, String company_email, String company_contact, String date, String hour, String residuo, String descresiduo) {
        this.client_idlogin = client_idlogin;
        this.client_name = client_name;
        this.client_email = client_email;
        this.company_idlogin = company_idlogin;
        this.company_name = company_name;
        this.company_email = company_email;
        this.company_contact = company_contact;
        this.date = date;
        this.hour = hour;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
    }

    public Request(long id, String client_idlogin, String client_name, String client_email, String company_idlogin, String company_name, String company_email, String company_contact, String date, String hour, String residuo, String descresiduo) {
        this.id = id;
        this.client_idlogin = client_idlogin;
        this.client_name = client_name;
        this.client_email = client_email;
        this.company_idlogin = company_idlogin;
        this.company_name = company_name;
        this.company_email = company_email;
        this.company_contact = company_contact;
        this.date = date;
        this.hour = hour;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
    }
    public Request() {

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

    public String getClient_idlogin() {
        return client_idlogin;
    }

    public void setClient_idlogin(String client_idlogin) {
        this.client_idlogin = client_idlogin;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getCompany_idlogin() {
        return company_idlogin;
    }

    public void setCompany_idlogin(String company_idlogin) {
        this.company_idlogin = company_idlogin;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getResiduo() {
        return residuo;
    }

    public void setResiduo(String residuo) {
        this.residuo = residuo;
    }

    public String getDescresiduo() {
        return descresiduo;
    }

    public void setDescresiduo(String descresiduo) {
        this.descresiduo = descresiduo;
    }

    public String getDatainc() {
        return datainc;
    }

    public void setDatainc(String datainc) {
        this.datainc = datainc;
    }

    public void print(){
        Log.v("SQLDatabase", "ContactLogin["+id+"]:" +" IDPK: "+idpk +" ClientId: " +client_idlogin+ " Nome Cliente: " +client_name+
                " Email Client: "+client_email+ " CompanyId: "+company_idlogin+ " Nome Empresa: "+company_name+
                " Email Empresa: "+company_email+" ContactLogin Empresa: " +company_contact+ " Data: " +date+
                " Hora: " +hour+ " Residuo: " + residuo+ " DescResiduo: "+descresiduo);
    }
}
