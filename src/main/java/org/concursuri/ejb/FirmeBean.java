package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

import org.concursuri.entities.Firme;

@Stateless
public class FirmeBean {
    private static final Logger LOG = Logger.getLogger(FirmeBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<Firme> findAllFirmeWithReprezentanti() {
        LOG.info("Find firme with reprezentanti");
        try {
            TypedQuery<Firme> query = entityManager.createQuery(
                    "SELECT DISTINCT f FROM Firme f " +
                            "LEFT JOIN FETCH f.reprezentanti r " +
                            "LEFT JOIN FETCH r.user u",
                    Firme.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
