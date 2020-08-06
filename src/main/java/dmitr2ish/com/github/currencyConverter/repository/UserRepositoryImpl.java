package dmitr2ish.com.github.currencyConverter.repository;

import dmitr2ish.com.github.currencyConverter.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    final private EntityManager manager;

    @Autowired
    public UserRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }


    @Override
    public void addUser(User user) {
        manager.persist(user);
    }

    @Override
    public void update(User user) {
        manager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return manager.createQuery("select c from User c").getResultList();
    }

    @Override
    public boolean isExist(User user) {
        return manager.contains(user);
    }

    @Override
    public void delete(User user) {
        manager.remove(user);
    }

    @Override
    public User getById(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getByLogin(String login) {
        List<User> user = manager.createQuery("select c from User c where c.login = :login")
                .setParameter("login", login)
                .getResultList();
        return user.get(0);
    }
}
