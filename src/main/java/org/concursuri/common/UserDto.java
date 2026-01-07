package org.concursuri.common;

public class UserDto {
    Integer id;
    String nume;
    String prenume;
    String email;
    Integer varsta;

    public Integer getVarsta() {
        return varsta;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public Integer getId() {
        return id;
    }

    public UserDto(Integer id, String nume, String prenume, String email, Integer varsta) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.varsta = varsta;
    }
}
