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
import org.concursuri.entities.Usergroups;

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

    public List<UserDto> findAllOrganizers() {
        LOG.info("Find applying organizator");
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.rol IN ('organizator') AND (u.accepted = false OR u.accepted IS NULL)",
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
            UserDto shittyUserDto = new UserDto(
                    user.getId(),
                    user.getNume(),
                    user.getPrenume(),
                    user.geteMail(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getTelefon(),
                    user.getRol(),
                    user.getBday(),
                    user.getAccepted()
            );
            usersDtos.add(shittyUserDto);
        }
        return usersDtos;
    }

    public void createUser(String nume, String prenume, String eMail, String username, String password, String telefon, String rol, Date bday, Boolean accepted) {
        try {
            User user = new User(nume, prenume, eMail, username, password, telefon, rol, bday, accepted);
            entityManager.persist(user);
        } catch (Exception e) {
            LOG.severe("Error creating user: " + e.getMessage());
            throw new EJBException(e);
        }
    }

    public void acceptUser(Integer userId) {
        LOG.info("Accepting user with id: " + userId);
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.setAccepted(true);
        }
        Usergroups groupMapping = new Usergroups(user.getUsername(), user.getRol());
        entityManager.persist(groupMapping);

        LOG.info("Added user " + user.getUsername() + " to group " + user.getRol());

    }

    public void acceptOrganizer(Integer userId) {
        LOG.info("Accepting organizer with id: " + userId);
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.setAccepted(true);
        }
    }

    public UserDto verifyUser(String username, String password) {
        LOG.info("Verifying login for: " + username);
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                    User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }

            // Return the first match (usernames should be unique)
            User user = users.get(0);
            return new UserDto(user.getId(), user.getNume(), user.getPrenume(), user.geteMail(), user.getUsername(), user.getPassword(), user.getTelefon(), user.getRol(), user.getBday(), user.getAccepted());
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public boolean isUserAccepted(String username) {
        LOG.info("Checking accepted status for: " + username);
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username",
                    User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                return false;
            }
            return Boolean.TRUE.equals(users.get(0).getAccepted());
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
}
