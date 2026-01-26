package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

import org.concursuri.entities.Reprezentanti;

@Stateless
public class ReprezentantiBean {
    private static final Logger LOG = Logger.getLogger(ReprezentantiBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<Reprezentanti> findAllReprezentanti() {
        LOG.info("Find reprezentanti with firme and users");
        try {
            TypedQuery<Reprezentanti> query = entityManager.createQuery(
                    "SELECT r FROM Reprezentanti r " +
                            "LEFT JOIN FETCH r.firma f " +
                            "LEFT JOIN FETCH r.user u",
                    Reprezentanti.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
