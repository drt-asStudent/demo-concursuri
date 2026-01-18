package org.concursuri.common;

import java.sql.Date;

public class UserDto {
    Integer id;
    String nume;
    String prenume;
    String eMail;



    String username;
    String password;
    String telefon;
    //Integer varsta;
    String rol;
    Date bday;
    Boolean accepted;

//    public Integer getVarsta() {
//        return varsta;
//    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public UserDto(Integer id, String nume, String prenume, String email, String username, String password, String telefon, String rol, /*Integer varsta,*/ Date bday, Boolean accepted) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.eMail = email;
        this.username = username;
        this.password = password;
        this.telefon = telefon;
        this.rol = rol;
        //this.varsta = varsta;
        this.bday = null;
        this.accepted = accepted;
    }

    public String getUsername() {
        return username;
    }

    public String geteMail() {
        return eMail;
    }
    public String getRol() {
        return rol;
    }

    public Integer getId() {
        return id;
    }

    public Date getBday() {
        return bday;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public String getPassword() {
        return password;
    }
}
