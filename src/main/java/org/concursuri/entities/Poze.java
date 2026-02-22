package org.concursuri.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "poze")
public class Poze {
    public static final String POZE_INAINTE = "prezentare";
    public static final String POZE_DUPA = "desfasurare";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    // NEW: link to concurs
    @Column(name = "id_concurs")
    private Integer idConcurs;
    @Column(name = "file_name")
    private String filename;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_content")
    private byte[] fileContent;
    @Column(name= "categoria")
    private String categoria;

    public Poze(String filename, String fileType, byte[] fileContent, String categoria) {
        this.filename = filename;
        this.fileType = fileType;
        this.fileContent = fileContent;
        this.categoria = categoria;
    }

    public Poze() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdConcurs() {
        return idConcurs;
    }

    public void setIdConcurs(Integer idConcurs) {
        this.idConcurs = idConcurs;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (!(POZE_INAINTE.equals(categoria) ||
                POZE_DUPA.equals(categoria) )) {
            throw new IllegalArgumentException("Invalid competitionType: " + categoria +
                    ". Allowed values: prezentare, desfasurare");
        }
        this.categoria = categoria;
    }
}