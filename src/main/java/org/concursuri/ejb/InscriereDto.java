package org.concursuri.common;

public class InscriereDto {
    private final Integer participareId;
    private final Integer concursId;
    private final String concursNume;

    private final String lucrare;
    private final String descriere;
    private final String profesorCoordonator;

    public InscriereDto(Integer participareId, Integer concursId, String concursNume,
                        String lucrare, String descriere, String profesorCoordonator) {
        this.participareId = participareId;
        this.concursId = concursId;
        this.concursNume = concursNume;
        this.lucrare = lucrare;
        this.descriere = descriere;
        this.profesorCoordonator = profesorCoordonator;
    }

    public Integer getParticipareId() {
        return participareId;
    }

    public Integer getConcursId() {
        return concursId;
    }

    public String getConcursNume() {
        return concursNume;
    }

    public String getLucrare() {
        return lucrare;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getProfesorCoordonator() {
        return profesorCoordonator;
    }
}
