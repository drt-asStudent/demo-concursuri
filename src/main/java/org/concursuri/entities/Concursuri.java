package org.concursuri.entities;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "concursuri")
public class Concursuri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nume", length = 70)
    private String nume;

    @Column(name = "data_desfasurare")
    private Date dataDesfasurare;

    @Column(name = "start_inscrieri")
    private Date startInscrieri;

    @Column(name = "stop_inscrieri")
    private Date stopInscrieri;

    public static final String TYPE_SOFTWARE = "software";
    public static final String TYPE_HARDWARE = "hardware";
    public static final String TYPE_MIXED = "mixed";

    @Column(name = "competition_type", length = 20)
    private String competitionType;

    @Column(name = "nivel")
    private String nivel;

    public Concursuri(String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, String competitionType, String nivel) {
        this.nume = nume;
        this.dataDesfasurare = dataDesfasurare;
        this.startInscrieri = startInscrieri;
        this.stopInscrieri = stopInscrieri;
        setCompetitionType(competitionType);
        this.nivel = nivel;
    }

    public String getCompetitionType() {
        return competitionType;
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

    public void setCompetitionType(String competitionType) {
        if (!(TYPE_SOFTWARE.equals(competitionType) ||
                TYPE_HARDWARE.equals(competitionType) ||
                TYPE_MIXED.equals(competitionType))) {
            throw new IllegalArgumentException("Invalid competitionType: " + competitionType +
                    ". Allowed values: software, hardware, mixed");
        }
        this.competitionType = competitionType;
    }
}
