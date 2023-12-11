package com.pucpr.greencycle.model;

import android.util.Log;

public class Client {
    private long id; // PK SQLite
    private long idpk; //PK MySQL remoto
    private String idlogin, name, email, cpf, phone, state, city, address, zipcode, country, residuo, descresiduo,datainc;

    public Client(long id, long idpk, String idlogin, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo, String descresiduo, String datainc) {
        this.id = id;
        this.idpk = idpk;
        this.idlogin = idlogin;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }

    public Client(long id, long idpk, String idlogin, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo, String descresiduo) {
        this.id = id;
        this.idpk = idpk;
        this.idlogin = idlogin;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }

    public Client(String idlogin, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo, String descresiduo) {
        this.idlogin = idlogin;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }


    public Client(long id, String idlogin, String name, String email, String cpf, String phone, String state, String city, String address, String zipcode, String country, String residuo,String descresiduo, String datainc) {
        this.id = id;
        this.idlogin = idlogin;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.country = country;
        this.residuo = residuo;
        this.descresiduo = descresiduo;
        this.datainc = datainc;
    }

    public Client() {

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

    public String getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(String idlogin) {
        this.idlogin = idlogin;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        Log.v("SQLDatabase", "ContactLogin["+id+"]:" + " IDPK: "+idpk +" Idlogin: " +idlogin+ " Nome: " +name+" Email: "+email+ " CPF: "+cpf+ " Cidade: " +city+ " Estado: " +state+
                " Endereço: " +address+ " Residio: " + residuo+ " DescResiduo: "+ descresiduo);
    }

}
