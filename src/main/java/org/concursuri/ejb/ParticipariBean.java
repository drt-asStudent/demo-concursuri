package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

import org.concursuri.entities.Participari;

@Stateless
public class ParticipariBean {
    private static final Logger LOG = Logger.getLogger(ParticipariBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public void createParticipare(String lucrare, String descriere, String profesorCoordonator, Integer idu, Integer idc) {
        LOG.info("Creating participare");
        try {
            Participari participare = new Participari(lucrare, descriere, profesorCoordonator, idu, idc);
            entityManager.persist(participare);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
