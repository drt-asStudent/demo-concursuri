package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.concursuri.common.ConcursDto;
import org.concursuri.entities.Concursuri;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ConcursBean {
    private static final Logger LOG = Logger.getLogger(ConcursBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<ConcursDto> findAllConcursuri() {
        LOG.info("Find all concursuri");
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery("SELECT c FROM Concursuri c", Concursuri.class);
            List<Concursuri> concursuri = query.getResultList();
            return copyConcursToDTO(concursuri);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<ConcursDto> findConcursuriNotParticipated(Integer idu) {
        LOG.info("Find concursuri not participated by user: " + idu);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c " +
                            "WHERE c.id NOT IN (" +
                            "SELECT p.idc FROM Participari p WHERE p.idu = :idu" +
                            ")",
                    Concursuri.class);
            query.setParameter("idu", idu);
            List<Concursuri> concursuri = query.getResultList();
            return copyConcursToDTO(concursuri);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<ConcursDto> findConcursuriByOrganizator(Integer iduOrganizator) {
        LOG.info("Find concursuri by organizator: " + iduOrganizator);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c WHERE c.iduOrganizator = :iduOrganizator",
                    Concursuri.class);
            query.setParameter("iduOrganizator", iduOrganizator);
            List<Concursuri> concursuri = query.getResultList();
            return copyConcursToDTO(concursuri);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<ConcursDto> copyConcursToDTO(List<Concursuri> concursuri) {
        List<ConcursDto> concursDtos = new ArrayList<>();
        for (Concursuri cx : concursuri) {
            ConcursDto dto = new ConcursDto(
                    cx.getId(),
                    cx.getNume(),
                    cx.getDataDesfasurare(),
                    cx.getStartInscrieri(),
                    cx.getStopInscrieri(),
                    cx.getSoftware(),
                    cx.getHardware(),
                    cx.getNivel()
            );
            concursDtos.add(dto);
        }
        return concursDtos;
    }

    public void createConcurs(String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, Boolean isSoftware, Boolean isHardware, String nivel, Integer iduOrganizator) {
        Concursuri concurs = new Concursuri(nume, dataDesfasurare, startInscrieri, stopInscrieri, isSoftware, isHardware, nivel);
        concurs.setIduOrganizator(iduOrganizator);
        entityManager.persist(concurs);
    }
}
