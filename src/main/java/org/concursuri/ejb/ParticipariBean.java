package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.concursuri.common.InscriereDto;
import org.concursuri.entities.Participari;

@Stateless
public class ParticipariBean {
    private static final Logger LOG = Logger.getLogger(ParticipariBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public void createParticipare(String lucrare, String descriere, String materiale, String profesorCoordonator, Integer idu, Integer idc) {
        LOG.info("Creating participare (nota=null)");
        try {
            Participari participare = new Participari(lucrare, descriere, materiale, profesorCoordonator, idu, idc);
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

    public List<Participari> findParticipariByConcursIdOrderByNotaDesc(Integer idc) {
        LOG.info("Find participari for concurs ordered by nota: " + idc);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p WHERE p.idc = :idc ORDER BY p.nota DESC",
                    Participari.class);
            query.setParameter("idc", idc);
            return query.getResultList();
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<Participari> findAcceptedParticipariByConcursId(Integer idc) {
        LOG.info("Find accepted participari for concurs: " + idc);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p WHERE p.idc = :idc AND p.accepted = 'YES'",
                    Participari.class);
            query.setParameter("idc", idc);
            return query.getResultList();
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public int countParticipariByConcursId(Integer idc) {
        LOG.info("Count participari for concurs: " + idc);
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(p) FROM Participari p WHERE p.idc = :idc",
                    Long.class
            );
            query.setParameter("idc", idc);
            Long cnt = query.getSingleResult();
            return cnt == null ? 0 : cnt.intValue();
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

    public List<InscriereDto> findInscrieriForUser(Integer idu) {
        LOG.info("Find inscrieri for user: " + idu);
        try {
            TypedQuery<Object[]> query = entityManager.createQuery(
                    "SELECT p.id, p.idc, c.nume, p.lucrare, p.descriere, p.profesorCoordonator, c.canceled, p.accepted " +
                            "FROM Participari p, Concursuri c " +
                            "WHERE p.idu = :idu AND c.id = p.idc AND c.dataDesfasurare >= CURRENT_DATE",
                    Object[].class);
            query.setParameter("idu", idu);

            List<Object[]> rows = query.getResultList();
            List<InscriereDto> result = new ArrayList<>();
            for (Object[] r : rows) {
                result.add(new InscriereDto((Integer) r[0], (Integer) r[1], (String) r[2], (String) r[3], (String) r[4], (String) r[5], (Integer) r[6], (String) r[7]
                ));
            }
            return result;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean updateAcceptedForOrganizator(Integer participareId, Integer idOrganizator, String accepted) {
        LOG.info("Updating accepted for participare: id=" + participareId + ", org=" + idOrganizator);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p, Concursuri c " +
                            "WHERE p.id = :pid AND p.idc = c.id AND c.idOrganizator = :oid",
                    Participari.class);
            query.setParameter("pid", participareId);
            query.setParameter("oid", idOrganizator);

            List<Participari> results = query.getResultList();
            if (results.isEmpty()) {
                return false;
            }
            Participari p = results.get(0);
            p.setAccepted(accepted);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public Participari findParticipareByIdForUser(Integer participareId, Integer idu) {
        LOG.info("Find participare by id for user: participareId=" + participareId + ", idu=" + idu);
        try {
            TypedQuery<Participari> query = entityManager.createQuery(
                    "SELECT p FROM Participari p WHERE p.id = :pid AND p.idu = :idu",
                    Participari.class);
            query.setParameter("pid", participareId);  //id-ul participării
            query.setParameter("idu", idu);            //id-ul utilizatorului

            List<Participari> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean updateParticipareFields(Integer participareId,Integer idu, String lucrare, String descriere, String profesorCoordonator) {
        LOG.info("Update participare fields: participareId=" + participareId + ", idu=" + idu);
        try {
            Participari p = findParticipareByIdForUser(participareId, idu);
            if (p == null) {
                return false;
            }

            p.setLucrare(lucrare);
            p.setDescriere(descriere);
            p.setProfesorCoordonator(profesorCoordonator);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean deleteParticipareByIdForUser(Integer participareId, Integer idu) {
        LOG.info("Delete participare: participareId=" + participareId + ", idu=" + idu);
        try {
            Participari p = findParticipareByIdForUser(participareId, idu);
            if (p == null) {
                return false;
            }
            entityManager.remove(p);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
