package com.pucpr.greencycle.model;

import android.content.Context;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private ArrayList<Contact>contacts;
    private ArrayList<Client>clients;
    private ArrayList<Company>companies;
    private ArrayList<Request>requests;
    private ArrayList<Request>requestcl;
    private ArrayList<Request>requestcp;


    private ContactDatabase database;
    Context context;

    public void dlTableContact(){
        database.deleteTableContact();
    }
    public void dlTableClient(){
        database.deleteTableClient();
    }
    public void dlTableCompany(){
        database.deleteTableCompany();
    }
    public void dlTableRequest(){
        database.deleteTableRequest();
    }
    public void crTableContact(Context context){
        database = new ContactDatabase(context);
        database.createTableContact();
    }
    public void crTableClient(Context context){
        database = new ContactDatabase(context);
        database.createTableClient();
    }
    public void crTableCompany(Context context){
        database = new ContactDatabase(context);
        database.createTableCompany();
    }
    public void crTableRequest(Context context){
        database = new ContactDatabase(context);
        database.createTableRequest();
    }
    public void crTable(String queryCreateTable){
        database = new ContactDatabase(context);
        database.createTable(queryCreateTable);

    }
    public void createDatabase(Context context){
        database = new ContactDatabase(context);
        contacts = database.getContactsFromDB();

    }
    public ArrayList<Contact>getContacts(){
        return contacts;
    }

    public Contact getContact(int pos){
        return contacts.get(pos);
    }
    public int getContactSize(){
        return contacts.size();
    }
    public boolean addContact(Contact c){
        long id = database.createContactInDB(c);
        if (id > 0){
            c.setId(id);
            contacts.add(c);
            return true;
        }
        return false;
    }

    public boolean insertContact(Contact c, int pos){
        long id = database.insertContactInDB(c);
        if (id > 0){
            contacts.add(pos,c);
            return true;
        }
        return false;
    }
    public boolean updateContact(Contact c, int pos){
        int count = database.updateContactInDB(c);
        if (count == 1){
            contacts.set(pos,c);
            return true;
        }
        return false;
    }
    public boolean removeContact(int pos){
        int count = database.removeContactInDB(
                getContact(pos)
        );
        if (count == 1){
            contacts.remove(pos);
            return true;
        }
        return false;
    }

    public void createClientDatabase(Context context){
        database = new ContactDatabase(context);
        clients = database.getClientsFromDB();
    }

    public ArrayList<Client>getClients(){
        return clients;
    }
    public Client getClient(int pos){
        return clients.get(pos);
    }
    public int getClientSize(){
        return clients.size();
    }
    public boolean addClient(Client c){
        long id = database.createClientInDB(c);
        if (id > 0){
            c.setId(id);
            clients.add(c);
            return true;
        }
        return false;
    }

    public boolean insertClient(Client c,int pos){
        long id = database.insertClientInDB(c);
        if (id > 0){
            clients.add(pos,c);
            return true;
        }
        return false;
    }

    public boolean updateClient(Client c, int pos){
        int count = database.updateClientInDB(c);
        if (count == 1){
            clients.set(pos,c);
            return true;
        }
        return false;
    }

    public boolean removeClient(int pos){
        int count = database.removeClientInDB(
                getClient(pos)
        );
        if (count == 1){
            contacts.remove(pos);
            return true;
        }
        return false;
    }
    public void createCompanyDatabase(Context context){
        database = new ContactDatabase(context);
        companies = database.getCompaniesFromDB();
    }
    public ArrayList<Company>getCompanies(){
        return companies;
    }

    public Company getCompany(int pos){
        return companies.get(pos);
    }
    public int getCompanySize(){
        return companies.size();
    }
    public boolean addCompany(Company c){
        long id = database.createCompanyInDB(c);
        if (id > 0){
            c.setId(id);
            companies.add(c);
            return true;
        }
        return false;
    }

    public boolean insertCompany(Company c,int pos){
        long id = database.insertCompanyInDB(c);
        if (id > 0){
            companies.add(pos,c);
            return true;
        }
        return false;
    }
    public boolean updateCompany(Company c, int pos){
        int count = database.updateCompanyInDB(c);
        if (count == 1){
            companies.set(pos,c);
            return true;
        }
        return false;
    }
    public boolean removeCompany(int pos){
        int count = database.removeCompanyInDB(
                getCompany(pos)
        );
        if (count == 1){
            companies.remove(pos);
            return true;
        }
        return false;
    }
    public void createRequestDatabase(Context context, String Email){
        database = new ContactDatabase(context);
        requests = database.getRequestsFromDB();
        requestcl = database.getRequestsClFromDB(Email);
        requestcp = database.getRequestsCpFromDB(Email);
    }
    public ArrayList<Request>getRequests(){
        return requests;
    }
    public ArrayList<Request>getRequestClCompany(){
        return requestcl;
    }
    public ArrayList<Request>getRequestCpCompany(){
        return requestcp;
    }
    public Request getRequest(int pos){
        return requests.get(pos);
    }
    public Request getRequestClient(int pos){
        return requestcl.get(pos);
    }
    public Request getRequestCompany(int pos){
        return requestcp.get(pos);
    }
    public int getRequestClSize(){
        return requestcl.size();
    }
    public int getRequestCpSize(){
        return requestcp.size();
    }
    public int getRequestSize(){
        return requests.size();
    }
    public boolean addRequest(Request c){
        long id = database.createRequestInDB(c);
        if (id > 0){
            c.setId(id);
            requests.add(c);
            return true;
        }
        return false;
    }

    public boolean insertRequest(Request c,int pos){
        long id = database.insertRequestInDB(c);
        if (id > 0){
            requests.add(pos,c);
            return true;
        }
        return false;
    }
    public boolean updateRequest(Request c, int pos){
        int count = database.updateRequestInDB(c);
        if (count == 1){
            requests.set(pos,c);
            return true;
        }
        return false;
    }
    public boolean removeRequest(int pos){
        int count = database.removeRequestInDB(
                getRequest(pos)
        );
        if (count == 1){
            requests.remove(pos);
            return true;
        }
        return false;
    }


}
