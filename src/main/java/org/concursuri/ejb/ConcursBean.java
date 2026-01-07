package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.concursuri.common.ConcursDto;
import org.concursuri.common.UserDto;
import org.concursuri.entities.Concursuri;
import org.concursuri.entities.User;

import java.util.ArrayList;
import java.util.Date;
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

    public void createConcurs(Integer id, String nume, Date dataDesfasurare, Date startInscrieri, Date stopInscrieri, Boolean isSoftware, Boolean isHardware, String nivel) {
        Concursuri concurs = new Concursuri(id, nume, dataDesfasurare, startInscrieri, stopInscrieri, isSoftware, isHardware, nivel);
        entityManager.persist(concurs);
    }
}
