package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.ValCurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class ValCurseRepositoryImpl implements ValCurseRepository {

    final private EntityManager entityManager;

    @Autowired
    public ValCurseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void add(ValCurse curse) {
        entityManager.persist(curse);
    }

    @Override
    public ValCurse getByName(String name) {
        return (ValCurse) entityManager.createQuery("select c from ValCurse c where c.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ValCurse> getByDate(Date date) {
        return entityManager.createQuery("select c from ValCurse c where c.date = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ValCurse> getAll() {
        return entityManager.createQuery("select  c from ValCurse c").getResultList();
    }
}
