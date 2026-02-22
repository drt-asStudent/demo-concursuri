package org.concursuri.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.concursuri.entities.Poze;

@Stateless
public class PozeBean {

    @PersistenceContext
    EntityManager entityManager;

    public Integer createPozaForConcurs(Integer idConcurs, String filename, String fileType, byte[] fileContent, String categoria) {
        if (idConcurs == null) {
            throw new IllegalArgumentException("idConcurs must not be null");
        }
        Poze p = new Poze();
        p.setIdConcurs(idConcurs);
        p.setFilename(filename);
        p.setFileType(fileType);
        p.setFileContent(fileContent);
        p.setCategoria(categoria);

        entityManager.persist(p);
        entityManager.flush();
        return p.getId();
    }
}
