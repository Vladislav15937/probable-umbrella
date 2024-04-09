package net.proselyte.springbootdemo.web.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.proselyte.springbootdemo.web.model.User;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public void deleteUsersById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUserIntoId(User user, Long id) {
        entityManager.detach(entityManager.find(User.class, id));
        entityManager.merge(user);
    }
}