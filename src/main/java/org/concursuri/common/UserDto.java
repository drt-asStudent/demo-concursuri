package org.concursuri.common;

import java.sql.Date;

public class UserDto {
    Integer id;
    String nume;
    String prenume;
    String eMail;
    Integer telefon;
    Integer varsta;
    String rol;
    Date bday;

    public Integer getVarsta() {
        return varsta;
    }

    public String getEmail() {
        return eMail;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public Integer getTelefon() {
        return telefon;
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

    public UserDto(Integer id, String nume, String prenume, String email, Integer telefon, String rol, Integer varsta, Date bday) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.eMail = email;
        this.telefon = telefon;
        this.rol = rol;
        this.varsta = varsta;
        this.bday = null;
    }
}
