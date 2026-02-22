package org.concursuri.common;

public record InscriereDto(Integer participareId, Integer concursId, String concursNume, String lucrare,
                           String descriere, String profesorCoordonator, Integer canceled) {
}
