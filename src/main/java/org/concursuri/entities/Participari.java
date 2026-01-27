package org.concursuri.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "participari")
public class Participari {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="lucrare")
    private String lucrare;

    @Column(name="descriere")
    private String descriere;

    @Column(name = "profesor_coordonator")
    private String profesorCoordonator;

    @Column(name = "idu")
    private Integer idu;

    @Column(name = "idc")
    private Integer idc;

    @Column(name="nota")
    private Integer nota;

    public Participari(String lucrare, String descriere, String profesorCoordonator, Integer idu, Integer idc, Integer nota) {
        this.lucrare = lucrare;
        this.descriere = descriere;
        this.profesorCoordonator = profesorCoordonator;
        this.idu = idu;
        this.idc = idc;
        this.nota = nota;
    }

    public Participari(String lucrare, String descriere, String profesorCoordonator, Integer idu, Integer idc) {
        this(lucrare, descriere, profesorCoordonator, idu, idc, null);
    }

    public Participari() {
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLucrare() {
        return lucrare;
    }

    public void setLucrare(String lucrare) {
        this.lucrare = lucrare;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getProfesorCoordonator() {
        return profesorCoordonator;
    }

    public void setProfesorCoordonator(String profesorCoordonator) {
        this.profesorCoordonator = profesorCoordonator;
    }

    public Integer getIdu() {
        return idu;
    }

    public void setIdu(Integer idu) {
        this.idu = idu;
    }

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(Integer idc) {
        this.idc = idc;
    }
}
