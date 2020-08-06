package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CurrensyRepositroyImpl implements CurrensyRepositroy {

    final private EntityManager entityManager;

    @Autowired
    public CurrensyRepositroyImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCurrency(Currency curr) {
        entityManager.persist(curr);
    }

    @Override
    public void update(Currency curr) {
        entityManager.merge(curr);
    }

    @Override
    public Currency getById(Long id) {
        return entityManager.find(Currency.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Currency> getAllCurrencies() {
        return entityManager.createQuery("select c from Currency c").getResultList();
    }

    @Override
    public boolean isExist(Currency curr) {
        Long count = (Long) entityManager.createQuery("select count(c) from Currency c").getSingleResult();
        return count.equals(0L);
    }

    @Override
    public void delete(Currency curr) {
        entityManager.remove(curr);
    }
}
