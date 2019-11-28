package com.germangascon.navigationdrawersample.modelo;

import java.util.ArrayList;

public class Account {
    private int id;
    private String name;
    private String firstSurname;
    private String email;
    private ArrayList<Contacto> contactos;
    private ArrayList<Mail> mails;

    public Account(int id, String name, String firstSurname, String email) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void setMails(ArrayList<Mail> mails) {
        this.mails = mails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
