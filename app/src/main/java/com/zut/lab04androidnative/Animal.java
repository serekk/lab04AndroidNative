package com.zut.lab04androidnative;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Animal implements Serializable {
    private Integer id;
    private String gatunek;
    private String kolor;
    private Float wielkosc;
    private String opis;

    public Animal() {
    }

    public Animal(String gatunek, String kolor, Float wielkosc, String opis) {
        this.gatunek = gatunek;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Zwierze: [id = %s, gatunek = %s, kolor = %s, wielkosc = %s, opis = %s ] ", id, gatunek, kolor, wielkosc, opis);
//        return "asd";
    }


    public Integer getId() {
        return id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public String getKolor() {
        return kolor;
    }

    public Float getWielkosc() {
        return wielkosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
