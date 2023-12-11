package com.pucpr.greencycle.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ContactDatabase extends SQLiteOpenHelper {
    //Iniciar Variaveis
    private static final String DB_NAME = "contacts.sqlite";
    private static final int DB_VERSION = 2;
    private static final String DB_TABLE = "Contact";
    private static final String DB_TABLE_CLIENT = "Cliente";
    private static final String DB_TABLE_COMPANY = "Empresa";
    private static final String DB_TABLE_REQUEST = "Solicitacao";
    private static final String COL_ID = "id";
    private static final String COL_IDPK = "idpk";
    private static final String COL_ID_LOGIN_COMPANY = "company_idlogin";
    private static final String COL_ID_LOGIN_CLIENT = "client_idlogin";
    private static final String COL_NAME =  "name";
    private static final String COL_NAME_COMPANY =  "company_name";
    private static final String COL_NAME_CLIENT =  "client_name";
    private static final String COL_EMAIL = "email";
    private static final String COL_EMAIL_COMPANY = "company_email";
    private static final String COL_EMAIL_CLIENT = "client_email";
    private static final String COL_CONTACT_COMPANY = "company_contact";
    private static final String COL_PASSWORD = "password";
    private static final String COL_OP = "op";
    private static final String COL_CPF = "cpf";
    private static final String COL_CNPJ = "cnpj";
    private static final String COL_PHONE = "phone";
    private static final String COL_STATE = "state";
    private static final String COL_CITY = "city";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIPCODE = "zipcode";
    private static final String COL_COUNTRY = "country";
    private static final String COL_RESIDUO = "residuo";
    private static final String COL_DESC_RESIDUO = "descresiduo";
    private static final String COL_REGION = "region";
    private static final String COL_DATE = "date";
    private static final String COL_HOUR = "hour";
    private static final String COL_DATAINC = "datainc";
    SQLiteDatabase sqLiteDatabase;
    //Criar Tabelas (Login, Cliente, Empresa e Solicitacoes)
    private static final String CREATE_TABLE_LOGIN = "Create Table if not exists "+DB_TABLE + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_IDPK + " INTEGER, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_PASSWORD + " TEXT, "+
            COL_OP + " TEXT, "+
            COL_DATAINC + " TEXT)";

    private static final String CREATE_TABLE_CLIENT = "Create Table if not exists "+DB_TABLE_CLIENT + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_IDPK + " INTEGER, "+
            COL_ID_LOGIN_CLIENT + " TEXT, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_CPF + " TEXT, "+
            COL_PHONE + " TEXT, "+
            COL_STATE + " TEXT, "+
            COL_CITY + " TEXT, "+
            COL_ADDRESS + " TEXT, "+
            COL_ZIPCODE + " TEXT, "+
            COL_COUNTRY + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO  + " TEXT, "+
            COL_DATAINC + " TEXT)";

    private static final String CREATE_TABLE_COMPANY = "Create Table if not exists "+DB_TABLE_COMPANY + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_IDPK + " INTEGER, "+
            COL_ID_LOGIN_COMPANY + " TEXT, "+
            COL_NAME + " TEXT, "+
            COL_EMAIL + " TEXT, "+
            COL_CNPJ + " TEXT, "+
            COL_PHONE + " TEXT, "+
            COL_STATE + " TEXT, "+
            COL_CITY + " TEXT, "+
            COL_ADDRESS + " TEXT, "+
            COL_ZIPCODE + " TEXT, "+
            COL_COUNTRY + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO + " TEXT, "+
            COL_REGION + " TEXT, "+
            COL_DATAINC + " TEXT)";

    private static final String CREATE_TABLE_REQUEST = "Create Table if not exists "+DB_TABLE_REQUEST + "( "+
            COL_ID + " Integer primary key autoincrement, "+
            COL_IDPK + " INTEGER, "+
            COL_ID_LOGIN_CLIENT + " TEXT, "+
            COL_NAME_CLIENT + " TEXT, "+
            COL_EMAIL_CLIENT + " TEXT, "+
            COL_ID_LOGIN_COMPANY + " TEXT, "+
            COL_NAME_COMPANY + " TEXT, "+
            COL_EMAIL_COMPANY + " TEXT, "+
            COL_CONTACT_COMPANY + " TEXT, "+
            COL_DATE + " TEXT, "+
            COL_HOUR + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO + " TEXT, "+
            COL_DATAINC + " TEXT)";
            /*
            COL_CNPJ + " TEXT, "+
            COL_CPF + " TEXT, "+
            COL_PHONE + " TEXT, "+
            COL_STATE + " TEXT, "+
            COL_CITY + " TEXT, "+
            COL_ADDRESS + " TEXT, "+
            COL_ZIPCODE + " TEXT, "+
            COL_COUNTRY + " TEXT, "+
            COL_RESIDUO + " TEXT, "+
            COL_DESC_RESIDUO + " TEXT, "+
            COL_REGION + " TEXT)";*/
    private Context context;
    public ContactDatabase(Context context){
        super(context, DB_NAME,null,DB_VERSION);
        Log.d("table", CREATE_TABLE_LOGIN);
        Log.d("table", CREATE_TABLE_CLIENT);
        Log.d("table", CREATE_TABLE_COMPANY);
        Log.d("table", CREATE_TABLE_REQUEST);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE_LOGIN);
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_TABLE_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE+"'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_CLIENT+"'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_COMPANY+"'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_REQUEST+"'");
        onCreate(sqLiteDatabase);
    }


    public void deleteTableContact(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DB_TABLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE+"'");
        }catch (Exception e){

        }

    }
    public void deleteTableClient(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DB_TABLE_CLIENT, null, null);
            db.execSQL("DROP TABLE IF EXISTS '"+DB_TABLE_CLIENT+"'");
        }catch (Exception e){
        }
    }
    public void deleteTableCompany() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DB_TABLE_COMPANY, null, null);
            db.execSQL("DROP TABLE IF EXISTS '" + DB_TABLE_COMPANY + "'");
        } catch (Exception e) {

        }
    }
    public void deleteTableRequest() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DB_TABLE_REQUEST, null, null);
            db.execSQL("DROP TABLE IF EXISTS '" + DB_TABLE_REQUEST + "'");
        } catch (Exception e) {

        }
    }
    public void createTableContact(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(CREATE_TABLE_LOGIN);
        }catch (SQLiteAbortException e){
        }
    }
    public void createTableClient(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(CREATE_TABLE_CLIENT);
        }catch (SQLiteAbortException e){
        }
    }
    public void createTableCompany(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(CREATE_TABLE_COMPANY);
        }catch (SQLiteAbortException e){
        }
    }
    public void createTableRequest(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(CREATE_TABLE_REQUEST);
        }catch (SQLiteAbortException e){
        }
    }
    public void createTable(String queryCreateTable){
        try {
            sqLiteDatabase.execSQL(queryCreateTable);
        }catch (SQLiteAbortException e){

        }

    }
    //Criar Metodo Insert para as Tabelas
    public long createContactInDB(Contact c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Login
        ContentValues values_login = new ContentValues();
        values_login.put(COL_IDPK, c.getIdpk());
        values_login.put(COL_NAME, c.getName());
        values_login.put(COL_EMAIL, c.getEmail());
        values_login.put(COL_PASSWORD, c.getPassword());
        values_login.put(COL_OP, c.getOp());
        values_login.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE, null, values_login);
        database.close();
        return id;
    }

    public long createClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_IDPK, c.getIdpk());
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_client.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_CLIENT, null, values_client);
        database.close();
        return id;
    }

    public long createCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_IDPK, c.getIdpk());
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
        values_company.put(COL_NAME, c.getName());
        values_company.put(COL_EMAIL, c.getEmail());
        values_company.put(COL_CNPJ, c.getCnpj());
        values_company.put(COL_PHONE, c.getPhone());
        values_company.put(COL_STATE, c.getState());
        values_company.put(COL_CITY, c.getCity());
        values_company.put(COL_ADDRESS, c.getAddress());
        values_company.put(COL_ZIPCODE, c.getZipcode());
        values_company.put(COL_COUNTRY, c.getCountry());
        values_company.put(COL_RESIDUO, c.getResiduo());
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        values_company.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_COMPANY, null, values_company);
        database.close();
        return id;
    }

    public long createRequestInDB(Request c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Request
        ContentValues values_request = new ContentValues();
        values_request.put(COL_IDPK, c.getIdpk());
        values_request.put(COL_ID_LOGIN_CLIENT, c.getClient_idlogin());
        values_request.put(COL_NAME_CLIENT, c.getClient_name());
        values_request.put(COL_EMAIL_CLIENT, c.getClient_email());
        values_request.put(COL_ID_LOGIN_COMPANY, c.getCompany_idlogin());
        values_request.put(COL_NAME_COMPANY, c.getCompany_name());
        values_request.put(COL_EMAIL_COMPANY, c.getCompany_email());
        values_request.put(COL_CONTACT_COMPANY, c.getCompany_contact());
        values_request.put(COL_DATE, c.getDate());
        values_request.put(COL_HOUR, c.getHour());
        values_request.put(COL_RESIDUO, c.getResiduo());
        values_request.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_request.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_REQUEST, null, values_request);
        database.close();
        return id;
    }



    //Criar Metodo Insert com ID para as Tabelas
    public long insertContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,c.getId());
        values.put(COL_IDPK,c.getIdpk());
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_OP,c.getOp());
        values.put(COL_DATAINC,c.getDatainc());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
    }

    public long insertClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_ID, c.getId());
        values_client.put(COL_IDPK,c.getIdpk());
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_client.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_CLIENT, null, values_client);
        database.close();
        return id;
    }

    public long insertCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_ID, c.getId());
        values_company.put(COL_IDPK, c.getIdpk());
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
        values_company.put(COL_NAME, c.getName());
        values_company.put(COL_EMAIL, c.getEmail());
        values_company.put(COL_CNPJ, c.getCnpj());
        values_company.put(COL_PHONE, c.getPhone());
        values_company.put(COL_STATE, c.getState());
        values_company.put(COL_CITY, c.getCity());
        values_company.put(COL_ADDRESS, c.getAddress());
        values_company.put(COL_ZIPCODE, c.getZipcode());
        values_company.put(COL_COUNTRY, c.getCountry());
        values_company.put(COL_RESIDUO, c.getResiduo());
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        values_company.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_COMPANY, null, values_company);
        database.close();
        return id;
    }
    public long insertRequestInDB(Request c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Request
        ContentValues values_request = new ContentValues();
        values_request.put(COL_ID, c.getId());
        values_request.put(COL_IDPK, c.getIdpk());
        values_request.put(COL_ID_LOGIN_CLIENT, c.getClient_idlogin());
        values_request.put(COL_NAME_CLIENT, c.getClient_name());
        values_request.put(COL_EMAIL_CLIENT, c.getClient_email());
        values_request.put(COL_ID_LOGIN_COMPANY, c.getCompany_idlogin());
        values_request.put(COL_NAME_COMPANY, c.getCompany_name());
        values_request.put(COL_EMAIL_COMPANY, c.getCompany_email());
        values_request.put(COL_CONTACT_COMPANY, c.getCompany_contact());
        values_request.put(COL_DATE, c.getDate());
        values_request.put(COL_HOUR, c.getHour());
        values_request.put(COL_RESIDUO, c.getResiduo());
        values_request.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_request.put(COL_DATAINC, c.getDatainc());
        long id = database.insert(DB_TABLE_REQUEST, null, values_request);
        database.close();
        return id;
    }

    //Criar Metodo GetContactsFromDB (Read)
    public ArrayList<Contact> getContactsFromDB(){
        ArrayList<Contact>contacts = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(
                        cursor.getColumnIndexOrThrow(COL_ID));
                int idpk = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_IDPK));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String password = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PASSWORD));
                String op = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_OP));
                String datainc = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATAINC));
                contacts.add(
                        new Contact(id,idpk,name,email,password,op,datainc));
            }while (cursor.moveToNext());
        }
        database.close();
        return contacts;
    }

    public ArrayList<Client> getClientsFromDB(){
        ArrayList<Client>clients = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_CLIENT, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                int idpk = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_IDPK));
                String idlogin = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cpf = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CPF));
                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE));
                String state = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_STATE));
                String city = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CITY));
                String address = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String zipcode = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZIPCODE));
                String country = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_COUNTRY));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                String datainc = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATAINC));
                clients.add(
                        new Client(id, idpk, idlogin, name, email, cpf, phone, state,  city, address, zipcode, country, residuo, descresiduo, datainc));
            }while (cursor.moveToNext());
        }
        database.close();
        return clients;
    }

    public ArrayList<Company> getCompaniesFromDB(){
        ArrayList<Company>companies = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_COMPANY, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                int idpk = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_IDPK));
                String idlogin = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_COMPANY));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME));
                String email = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL));
                String cnpj = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CNPJ));
                String phone = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_PHONE));
                String state = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_STATE));
                String city = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CITY));
                String address = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String zipcode = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ZIPCODE));
                String country = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_COUNTRY));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                String region = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_REGION));
                String datainc = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATAINC));
                companies.add(
                        new Company(id, idpk, idlogin, name, email, cnpj, phone, state,  city, address, zipcode, country, residuo, descresiduo, region, datainc));
            }while (cursor.moveToNext());
        }
        database.close();
        return companies;
    }
    public ArrayList<Request> getRequestsFromDB(){
        ArrayList<Request>requests = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_REQUEST, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                int idpk = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_IDPK));
                String clientid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String clientname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_CLIENT));
                String clientemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_CLIENT));
                String companyid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_COMPANY));
                String companyname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_COMPANY));
                String companyemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_COMPANY));
                String companycontact = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CONTACT_COMPANY));
                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATE));
                String hour = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_HOUR));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                String datainc = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATAINC));
                requests.add(
                        new Request(id, idpk,clientid, clientname,clientemail, companyid, companyname, companyemail, companycontact, date, hour, residuo, descresiduo,datainc));
            }while (cursor.moveToNext());
        }
        database.close();
        return requests;
    }
    public ArrayList<Request> getRequestsClFromDB(String EmailClient){
        ArrayList<Request>requestcl = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_REQUEST, null, ""+ COL_EMAIL_CLIENT+ "=?",new String[]{EmailClient}, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String clientid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String clientname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_CLIENT));
                String clientemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_CLIENT));
                String companyid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_COMPANY));
                String companyname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_COMPANY));
                String companyemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_COMPANY));
                String companycontact = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CONTACT_COMPANY));
                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATE));
                String hour = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_HOUR));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                requestcl.add(
                        new Request(id, clientid, clientname,clientemail, companyid, companyname, companyemail, companycontact, date, hour, residuo, descresiduo));
            }while (cursor.moveToNext());
        }
        database.close();
        return requestcl;
    }
    public ArrayList<Request> getRequestsCpFromDB(String EmailCompany){
        ArrayList<Request>requestcp = new ArrayList<>();
        //Get ReadAble Database
        SQLiteDatabase database = getReadableDatabase();
        // Raw Query
        Cursor cursor = database.query(DB_TABLE_REQUEST, null, ""+ COL_EMAIL_COMPANY +"=?",new String[]{EmailCompany}, null, null, null);
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String clientid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_CLIENT));
                String clientname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_CLIENT));
                String clientemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_CLIENT));
                String companyid = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_ID_LOGIN_COMPANY));
                String companyname = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME_COMPANY));
                String companyemail = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_EMAIL_COMPANY));
                String companycontact = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_CONTACT_COMPANY));
                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DATE));
                String hour = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_HOUR));
                String residuo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_RESIDUO));
                String descresiduo = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_DESC_RESIDUO));
                requestcp.add(
                        new Request(id, clientid, clientname,clientemail, companyid, companyname, companyemail, companycontact, date, hour, residuo, descresiduo));
            }while (cursor.moveToNext());
        }
        database.close();
        return requestcp;
    }
    //Criar Metodo Update para as Tabelas
    public int updateContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_IDPK,c.getIdpk());
        values.put(COL_NAME,c.getName());
        values.put(COL_EMAIL,c.getEmail());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_OP,c.getOp());
        values.put(COL_DATAINC,c.getDatainc());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE, values,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int updateClientInDB(Client c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Client
        ContentValues values_client = new ContentValues();
        values_client.put(COL_IDPK, c.getIdpk());
        values_client.put(COL_ID_LOGIN_CLIENT, c.getIdlogin());
        values_client.put(COL_NAME, c.getName());
        values_client.put(COL_EMAIL, c.getEmail());
        values_client.put(COL_CPF, c.getCpf());
        values_client.put(COL_PHONE, c.getPhone());
        values_client.put(COL_STATE, c.getState());
        values_client.put(COL_CITY, c.getCity());
        values_client.put(COL_ADDRESS, c.getAddress());
        values_client.put(COL_ZIPCODE, c.getZipcode());
        values_client.put(COL_COUNTRY, c.getCountry());
        values_client.put(COL_RESIDUO, c.getResiduo());
        values_client.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_client.put(COL_DATAINC, c.getDatainc());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE_CLIENT, values_client,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int updateCompanyInDB(Company c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Company
        ContentValues values_company = new ContentValues();
        values_company.put(COL_IDPK, c.getIdpk());
        values_company.put(COL_ID_LOGIN_COMPANY, c.getIdlogin());
        values_company.put(COL_NAME, c.getName());
        values_company.put(COL_EMAIL, c.getEmail());
        values_company.put(COL_CNPJ, c.getCnpj());
        values_company.put(COL_PHONE, c.getPhone());
        values_company.put(COL_STATE, c.getState());
        values_company.put(COL_CITY, c.getCity());
        values_company.put(COL_ADDRESS, c.getAddress());
        values_company.put(COL_ZIPCODE, c.getZipcode());
        values_company.put(COL_COUNTRY, c.getCountry());
        values_company.put(COL_RESIDUO, c.getResiduo());
        values_company.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_company.put(COL_REGION, c.getRegion());
        values_company.put(COL_DATAINC, c.getDatainc());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE_COMPANY, values_company,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int updateRequestInDB(Request c) {
        //Get Writable Database
        SQLiteDatabase database = getWritableDatabase();
        //Criar ContentValues Tabela Request
        ContentValues values_request = new ContentValues();
        values_request.put(COL_ID, c.getId());
        values_request.put(COL_IDPK, c.getIdpk());
        values_request.put(COL_ID_LOGIN_CLIENT, c.getClient_idlogin());
        values_request.put(COL_NAME_CLIENT, c.getClient_name());
        values_request.put(COL_EMAIL_CLIENT, c.getClient_email());
        values_request.put(COL_ID_LOGIN_COMPANY, c.getCompany_idlogin());
        values_request.put(COL_NAME_COMPANY, c.getCompany_name());
        values_request.put(COL_EMAIL_COMPANY, c.getCompany_email());
        values_request.put(COL_CONTACT_COMPANY, c.getCompany_contact());
        values_request.put(COL_DATE, c.getDate());
        values_request.put(COL_HOUR, c.getHour());
        values_request.put(COL_RESIDUO, c.getResiduo());
        values_request.put(COL_DESC_RESIDUO, c.getDescresiduo());
        values_request.put(COL_DATAINC, c.getDatainc());
        String id = String.valueOf(c.getId());
        int count = database.update(DB_TABLE_REQUEST, values_request,
                COL_ID + "=?", new String[]{id} );
        database.close();
        return count;
    }

    //Criar Metodo Remove para as Tabelas
    public int removeContactInDB(Contact c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeClientInDB(Client c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE_CLIENT,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeCompanyInDB(Company c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE_COMPANY,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }
    public int removeRequestInDB(Request c){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(c.getId());
        int count = database.delete(DB_TABLE_REQUEST,
                COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }


}
