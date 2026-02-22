package org.concursuri.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.concursuri.entities.Poze;
import org.concursuri.common.PozeDto;

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

    public PozeDto findPozaById(Integer id) {
        if (id == null) return null;
        Poze p = entityManager.find(Poze.class, id);
        if (p == null) return null;
        return new PozeDto(p.getId(), p.getFilename(), p.getFileType(), p.getFileContent(), p.getCategoria());
    }

    public PozeDto findPrezentareByConcurs(Integer idConcurs) {
        if (idConcurs == null) return null;
        try {
            TypedQuery<Poze> q = entityManager.createQuery(
                    "SELECT p FROM Poze p WHERE p.idConcurs = :idc AND p.categoria = :cat ORDER BY p.id DESC",
                    Poze.class
            );
            q.setParameter("idc", idConcurs);
            q.setParameter("cat", Poze.POZE_INAINTE);
            q.setMaxResults(1);
            java.util.List<Poze> res = q.getResultList();
            if (res.isEmpty()) return null;
            Poze p = res.get(0);
            return new PozeDto(p.getId(), p.getFilename(), p.getFileType(), p.getFileContent(), p.getCategoria());
        } catch (Exception e) {
            throw new jakarta.ejb.EJBException(e);
        }
    }
}
