package org.concursuri.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.concursuri.common.UserDto;
import org.concursuri.entities.User;

@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("Find all users");
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();
            return copyUsersToDTO(users);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<UserDto> copyUsersToDTO(List<User> users) {
        List<UserDto> carDtos = new ArrayList<>();
        for (User user : users) {
            UserDto dto = new UserDto(
                    user.getId(),
                    user.getNume(),
                    user.getPrenume(),
                    user.getEMail(),
                    user.getTelefon(),
                    user.getRol(),
                    user.getVarsta(),
                    user.getBday()
            );
            carDtos.add(dto);
        }
        return carDtos;
    }

    public void createUser(Integer id, String nume, String prenume, String eMail, Integer telefon, String rol, Integer varsta, Date bday) {
        User user = new User(id,nume,prenume,eMail,telefon,rol,varsta,bday);
        entityManager.persist(user);
    }
}
