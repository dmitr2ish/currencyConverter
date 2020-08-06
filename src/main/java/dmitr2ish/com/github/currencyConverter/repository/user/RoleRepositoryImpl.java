package dmitr2ish.com.github.currencyConverter.repository.user;

import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    final private EntityManager manager;

    @Autowired
    public RoleRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Role getByName(String name) {
        return manager.createQuery("select c from Role c where c.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAll() {
        return manager.createQuery("select c from Role c").getResultList();
    }

    @Override
    public void add(Role role) {
        manager.persist(role);
    }

}
