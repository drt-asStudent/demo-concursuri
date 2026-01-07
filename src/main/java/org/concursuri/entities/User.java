package org.concursuri.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "e_mail", length = 40)
    private String eMail;

    @Column(name = "telefon")
    private Integer telefon;

    @Column(name = "varsta")
    private Integer varsta;

    @Column(name = "categorie_participare", length = 15)
    private Integer categorieParticipare;

    @Column(name = "rol", length = 10)
    private String rol;

    @Column(name = "prenume", length = 40)
    private String prenume;

    @Column(name = "nume", length = 25)
    private String nume;

    public User(Integer id, String nume, String prenume, String eMail, Integer telefon, String rol, Integer varsta) {
    this.id = id;
    this.nume = nume;
    this.prenume = prenume;
    this.eMail = eMail;
    this.telefon = telefon;
    this.rol = rol;
    this.varsta = varsta;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public Integer getCategorieParticipare() {
        return categorieParticipare;
    }

    public void setCategorieParticipare(Integer categorieParticipare) {
        this.categorieParticipare = categorieParticipare;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(){}
}