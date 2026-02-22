package org.concursuri.common;

public record InscriereDto(Integer participareId, Integer concursId, String concursNume, String lucrare,
                           String descriere, String profesorCoordonator, Integer canceled, String accepted) {
    // JSP EL expects JavaBean-style getters.
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

    public Integer getCanceled() {
        return canceled;
    }

    public String getAccepted() {
        return accepted;
    }
}
