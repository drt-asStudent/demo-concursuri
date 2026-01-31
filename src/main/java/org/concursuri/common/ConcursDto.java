package org.concursuri.common;

import java.sql.Date;

public class ConcursDto {
    Integer id;
    String nume;
    Date dataDesfasurare;
    Date startInscrieri;
    Date stopInscrieri;
    String competitionType;
    String nivel;

    public String getNivel() {
        return nivel;
    }

    public Integer getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public java.sql.Date getDataDesfasurare() {
        return dataDesfasurare;
    }

    public Date getStartInscrieri() {
        return startInscrieri;
    }

    public Date getStopInscrieri() {
        return stopInscrieri;
    }

    public ConcursDto(Integer id, String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, String competitionType, String nivel) {
        this.id = id;
        this.nume = nume;
        this.dataDesfasurare = dataDesfasurare;
        this.startInscrieri = startInscrieri;
        this.stopInscrieri = stopInscrieri;
        this.competitionType = competitionType;
        this.nivel = nivel;
    }

    public String getCompetitionType() {
        return competitionType;
    }
}
