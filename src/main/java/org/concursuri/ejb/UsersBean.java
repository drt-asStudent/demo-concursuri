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
        LOG.info("Find accepted users");
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.rol IN ('elev', 'student') AND u.accepted = true",
                    User.class);
            List<User> users = query.getResultList();
            return copyUsersToDTO(users);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<UserDto> findApplying() {
        LOG.info("Find applying users");
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.rol IN ('elev', 'student') AND (u.accepted = false OR u.accepted IS NULL)",
                    User.class);
            List<User> users = query.getResultList();
            return copyUsersToDTO(users);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<UserDto> copyUsersToDTO(List<User> users) {
        List<UserDto> usersDtos = new ArrayList<>();
        for (User user : users) {
            UserDto dto = new UserDto(
                    user.getId(),
                    user.getNume(),
                    user.getPrenume(),
                    user.getEMail(),
                    user.getTelefon(),
                    user.getRol(),
                    user.getVarsta(),
                    user.getBday(),
                    user.getAccepted()
            );
            usersDtos.add(dto);
        }
        return usersDtos;
    }

    public void createUser(Integer id, String nume, String prenume, String eMail, String telefon, String rol, Integer varsta, Date bday, Boolean accepted) {
        User user = new User(id,nume,prenume,eMail,telefon,rol,varsta,bday,accepted);
        entityManager.persist(user);
    }
    public void acceptUser(Integer userId) {
        LOG.info("Accepting user with id: " + userId);
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.setAccepted(true);
        }
    }
}
