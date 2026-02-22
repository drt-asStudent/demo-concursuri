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
            TypedQuery<Concursuri> query = entityManager.createQuery("SELECT c FROM Concursuri c order by c.dataDesfasurare ASC", Concursuri.class);
            List<Concursuri> concursuri = query.getResultList();
            return copyConcursToDTO(concursuri);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public ConcursDto findConcursById(Integer idc) {
        LOG.info("Find concurs by id: " + idc);
        try {
            Concursuri c = entityManager.find(Concursuri.class, idc);
            if (c == null) {
                return null;
            }
            return new ConcursDto(
                    c.getId(),
                    c.getNume(),
                    c.getDetaliiConcurs(),
                    c.getDataDesfasurare(),
                    c.getStartInscrieri(),
                    c.getStopInscrieri(),
                    c.getCompetitionType(),
                    c.getNivel(),
                    c.getMinPart(),
                    c.getMaxPart(),
                    c.getCanceled()
            );
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
                            ") " +
                            "ORDER BY c.dataDesfasurare ASC",
                    Concursuri.class);
            query.setParameter("idu", idu);
            List<Concursuri> concursuri = query.getResultList();
            return copyConcursToDTO(concursuri);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<ConcursDto> findConcursuriByOrganizator(Integer idOrganizator) {
        LOG.info("Find concursuri for organizator id: " + idOrganizator);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c WHERE c.idOrganizator = :ido ORDER BY c.id",
                    Concursuri.class
            );
            query.setParameter("ido", idOrganizator);
            return copyConcursToDTO(query.getResultList());
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
                    cx.getDetaliiConcurs(),
                    cx.getDataDesfasurare(),
                    cx.getStartInscrieri(),
                    cx.getStopInscrieri(),
                    cx.getCompetitionType(),
                    cx.getNivel(),
                    cx.getMinPart(),
                    cx.getMaxPart(),
                    cx.getCanceled()
            );
            concursDtos.add(dto);
        }
        return concursDtos;
    }

    public Integer createConcurs(String nume, String detaliiConcurs, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri,
                              String competitionType, String nivel, int minPart, int maxPart, Integer idOrganizator) {
        if (minPart > maxPart) {
            throw new IllegalArgumentException("minPart must be <= maxPart");
        }
        if (idOrganizator == null) {
            throw new IllegalArgumentException("idOrganizator must not be null");
        }

        Concursuri concurs = new Concursuri(nume, detaliiConcurs, dataDesfasurare, startInscrieri, stopInscrieri, competitionType, nivel);
        concurs.setMinPart(minPart);
        concurs.setMaxPart(maxPart);
        concurs.setIdOrganizator(idOrganizator);
        concurs.setCanceled(0);

        entityManager.persist(concurs);
        entityManager.flush(); // ensures ID is generated now

        return concurs.getId();
    }

    public ConcursDto findConcursByIdForOrganizator(Integer idc, Integer idOrganizator) {
        LOG.info("Find concurs by id for organizator: idc=" + idc + ", idOrganizator=" + idOrganizator);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c WHERE c.id = :idc AND c.idOrganizator = :ido",
                    Concursuri.class
            );
            query.setParameter("idc", idc);
            query.setParameter("ido", idOrganizator);
            List<Concursuri> results = query.getResultList();
            if (results.isEmpty()) {
                return null;
            }
            Concursuri c = results.get(0);
            return new ConcursDto(
                    c.getId(),
                    c.getNume(),
                    c.getDetaliiConcurs(),
                    c.getDataDesfasurare(),
                    c.getStartInscrieri(),
                    c.getStopInscrieri(),
                    c.getCompetitionType(),
                    c.getNivel(),
                    c.getMinPart(),
                    c.getMaxPart(),
                    c.getCanceled()
            );
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean updateConcursForOrganizator(Integer idc,
                                               Integer idOrganizator,
                                               String nume,
                                               String detaliiConcurs,
                                               Date dataDesfasurare,
                                               Date startInscrieri,
                                               Date stopInscrieri,
                                               String competitionType,
                                               String nivel,
                                               int minPart,
                                               int maxPart) {
        LOG.info("Update concurs: idc=" + idc + ", idOrganizator=" + idOrganizator);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c WHERE c.id = :idc AND c.idOrganizator = :ido",
                    Concursuri.class
            );
            query.setParameter("idc", idc);
            query.setParameter("ido", idOrganizator);
            List<Concursuri> results = query.getResultList();
            if (results.isEmpty()) {
                return false;
            }

            Concursuri c = results.get(0);
            c.setNume(nume);
            c.setDetaliiConcurs(detaliiConcurs);
            c.setDataDesfasurare(dataDesfasurare);
            c.setStartInscrieri(startInscrieri);
            c.setStopInscrieri(stopInscrieri);
            c.setCompetitionType(competitionType);
            c.setNivel(nivel);
            c.setMinPart(minPart);
            c.setMaxPart(maxPart);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean cancelConcursForOrganizator(Integer idc, Integer idOrganizator) {
        LOG.info("Cancel concurs: idc=" + idc + ", idOrganizator=" + idOrganizator);
        try {
            TypedQuery<Concursuri> query = entityManager.createQuery(
                    "SELECT c FROM Concursuri c WHERE c.id = :idc AND c.idOrganizator = :ido",
                    Concursuri.class
            );
            query.setParameter("idc", idc);
            query.setParameter("ido", idOrganizator);
            List<Concursuri> results = query.getResultList();
            if (results.isEmpty()) {
                return false;
            }
            Concursuri c = results.get(0);
            c.setCanceled(1);
            return true;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
