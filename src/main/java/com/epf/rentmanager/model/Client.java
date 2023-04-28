package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Client {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;

    public long getId() {
        return id;
    }

    public void setId(long Id) {
        id = Id;
    }

    public Client(String nom, String prenom, String email, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    public Client( long Id, String nom, String prenom, String email, LocalDate naissance) {
        id = Id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id= " + id +
                ", nom= " + nom + '\'' +
                ", prenom= " + prenom + '\'' +
                ", email= " + email + '\'' +
                ", naissance= " + naissance +
                '}';
    }
    public static boolean isLegal(LocalDate BirthDate) {

        return BirthDate.until(LocalDate.now(), ChronoUnit.YEARS) >= 18;
    }

}
