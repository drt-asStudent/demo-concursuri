package org.concursuri.common;

import org.concursuri.entities.Poze;

import java.io.Serializable;

/**
 * DTO for {@link Poze}
 */
public record PozeDto(Integer id, String filename, String fileType, byte[] fileContent,
                      String categoria) implements Serializable {

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "filename = " + filename + ", " +
                "fileType = " + fileType + ", " +
                "fileContent = " + fileContent + ", " +
                "categoria = " + categoria + ")";
    }
}