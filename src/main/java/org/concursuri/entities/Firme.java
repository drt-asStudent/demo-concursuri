package org.concursuri.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "firme")
public class Firme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firma", length = 70)
    private String firma;

    @Column(name = "descriere", length = 255)
    private String descriere;

    @OneToMany(mappedBy = "firma")
    private List<Reprezentanti> reprezentanti;

    public Firme(String firma, String descriere) {
        this.firma = firma;
        this.descriere = descriere;
    }
    public Firme(){}

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getFirma() {
        return firma;
    }
    public void setFirma(String firma) {
        this.firma = firma;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Reprezentanti> getReprezentanti() {
        return reprezentanti;
    }

    public void setReprezentanti(List<Reprezentanti> reprezentanti) {
        this.reprezentanti = reprezentanti;
    }
}
