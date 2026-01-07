package org.concursuri.common;

import java.util.Date;

public class ConcursDto {
    Integer id;
    String nume;
    Date dataDesfasurare;
    Date startInscrieri;
    Date stopInscrieri;
    Boolean isSoftware;
    Boolean isHardware;
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

    public Date getDataDesfasurare() {
        return dataDesfasurare;
    }

    public Date getStartInscrieri() {
        return startInscrieri;
    }

    public Date getStopInscrieri() {
        return stopInscrieri;
    }

    public Boolean getIsSoftware() {
        return isSoftware;
    }

    public Boolean getIsHardware() {
        return isHardware;
    }

    public ConcursDto(Integer id, String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, Boolean isSoftware, Boolean isHardware, String nivel) {
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
