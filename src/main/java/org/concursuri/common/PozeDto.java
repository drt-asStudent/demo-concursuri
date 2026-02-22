package org.concursuri.common;

import org.concursuri.entities.Poze;

import java.io.Serializable;

/**
 * DTO for {@link Poze}
 */
public record PozeDto(Integer id, String filename, String fileType, byte[] fileContent,
                      String categoria) implements Serializable {

    // JSP EL expects JavaBean-style getters.
    public Integer getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public String getCategoria() {
        return categoria;
    }

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
