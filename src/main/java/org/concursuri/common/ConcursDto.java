package org.concursuri.common;

import java.sql.Date;

public class ConcursDto {
    Integer id;
    String nume;
    String detaliiConcurs;
    Date dataDesfasurare;
    Date startInscrieri;
    Date stopInscrieri;
    String competitionType;
    String nivel;

    int minPart;
    // seats (entity field is maxPart; JSP currently uses numarLocuri)
    int maxPart;

    // how many registered (computed for index.jsp)
    Integer registeredCount;

    public String getNivel() {
        return nivel;
    }

    public Integer getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public ConcursDto(Integer id, String nume, String detaliiConcurs, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri,
                      String competitionType, String nivel, int minPart, int maxPart) {
        this.id = id;
        this.nume = nume;
        this.detaliiConcurs = detaliiConcurs;
        this.dataDesfasurare = dataDesfasurare;
        this.startInscrieri = startInscrieri;
        this.stopInscrieri = stopInscrieri;
        this.competitionType = competitionType;
        this.nivel = nivel;
        this.minPart = minPart;
        this.maxPart = maxPart;
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

    public String getDetaliiConcurs() {
        return detaliiConcurs;
    }

    public String getCompetitionType() {
        return competitionType;
    }

    public int getMaxPart() {
        return maxPart;
    }

    // Alias so EL can use ${concursuri.numarLocuri}
    public int getNumarLocuri() {
        return maxPart;
    }

    public Integer getRegisteredCount() {
        return registeredCount;
    }

    public void setRegisteredCount(Integer registeredCount) {
        this.registeredCount = registeredCount;
    }
}
