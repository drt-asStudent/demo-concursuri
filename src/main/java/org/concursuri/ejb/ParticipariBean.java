package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
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

    public List<Participari> findParticipariByConcursId(Integer idc) {
        LOG.info("Find participari for concurs: " + idc);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p WHERE p.idc = :idc",
                    Participari.class);
            query.setParameter("idc", idc);
            return query.getResultList();
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean updateNota(Integer idu, Integer idc, String lucrare, Integer nota) {
        LOG.info("Updating nota for participare: idu=" + idu + ", idc=" + idc + ", lucrare=" + lucrare);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p WHERE p.idu = :idu AND p.idc = :idc AND p.lucrare = :lucrare",
                    Participari.class);
            query.setParameter("idu", idu);
            query.setParameter("idc", idc);
            query.setParameter("lucrare", lucrare);
            List<Participari> results = query.getResultList();
            if (results.isEmpty()) {
                return false;
            }
            Participari participare = results.get(0);
            participare.setNota(nota);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
