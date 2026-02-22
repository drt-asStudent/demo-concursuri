package org.concursuri.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "e_mail", length = 40)
    private String eMail;

    @Column(name = "telefon")
    private String telefon;

//    @Column(name = "varsta")
//    private Integer varsta;

    @Column(name = "rol", length = 10)
    private String rol;

    @Column(name = "prenume", length = 40)
    private String prenume;

    @Column(name = "nume", length = 25)
    private String nume;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "bday")
    private Date bday;

    @Column(name = "username", length = 225)
    private String username;

    @Column(name = "password", length = 225)
    private String password;

    public User(String nume, String prenume, String eMail, String username, String password, String telefon, String rol, /*Integer varsta,*/ Date bday, Boolean accepted) {
    this.nume = nume;
    this.prenume = prenume;
    this.eMail = eMail;
    this.username = username;
    this.password = password;
    this.telefon = telefon;
    this.rol = rol;
    //this.varsta = varsta;
    this.bday = bday;
    this.accepted = accepted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

//    public Integer getVarsta() {
//        return varsta;
//    }
//
//    public void setVarsta(Integer varsta) {
//        this.varsta = varsta;
//    }

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
