package com.epf.rentmanager.model;

import java.util.Optional;

public class Vehicle {
    private long id;
    private String constructeur;
    private int nb_places;

    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Vehicle (String constructeur, int nb_places) {
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    public Vehicle (long id, String constructeur, int nb_places) {

        this.id = id;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }
    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }
}
