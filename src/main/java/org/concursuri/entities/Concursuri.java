package org.concursuri.entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "concursuri")
public class Concursuri {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nume", length = 70)
    private String nume;

    @Column(name = "data_desfasurare")
    private Date dataDesfasurare;

    @Column(name = "start_inscrieri")
    private Date startInscrieri;

    @Column(name = "is_software")
    private Boolean isSoftware;

    @Column(name = "is_hardware")
    private Boolean isHardware;

    @Column(name = "nivel")
    private String nivel;

    public Boolean getSoftware() {
        return isSoftware;
    }

    public void setSoftware(Boolean software) {
        isSoftware = software;
    }

    public Boolean getHardware() {
        return isHardware;
    }

    public void setHardware(Boolean hardware) {
        isHardware = hardware;
    }



    public Date getStopInscrieri() {
        return stopInscrieri;
    }

    public void setStopInscrieri(Date stopInscrieri) {
        this.stopInscrieri = stopInscrieri;
    }

    public Date getStartInscrieri() {
        return startInscrieri;
    }

    public void setStartInscrieri(Date startInscrieri) {
        this.startInscrieri = startInscrieri;
    }

    @Column(name = "stop_inscrieri")
    private Date stopInscrieri;

    public Date getDataDesfasurare() {
        return dataDesfasurare;
    }
    public void setDataDesfasurare(Date dataDesfasurare) {
        this.dataDesfasurare = dataDesfasurare;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Concursuri() {

    }

    public Concursuri(Integer id, String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, Boolean isSoftware, Boolean isHardware, String nivel) {
        this.id = id;
        this.nume = nume;
        this.dataDesfasurare = dataDesfasurare;
        this.startInscrieri = startInscrieri;
        this.stopInscrieri = stopInscrieri;
        this.isSoftware = isSoftware;
        this.isHardware = isHardware;
        this.nivel = nivel;
    }
}
