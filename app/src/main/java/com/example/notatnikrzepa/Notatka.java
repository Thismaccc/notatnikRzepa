package com.example.notatnikrzepa;

import com.google.gson.annotations.SerializedName;

public class Notatka {
    private String tytul;
    private String tresc;
    private String data;

    public Notatka(String tytul, String tresc, String data) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.data = data;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
