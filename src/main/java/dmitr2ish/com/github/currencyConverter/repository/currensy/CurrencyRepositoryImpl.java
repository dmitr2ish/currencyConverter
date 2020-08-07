package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    final private EntityManager entityManager;

    @Autowired
    public CurrencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCurrency(Currency curr) {
        entityManager.persist(curr);
    }

    @Override
    public Currency update(Currency curr) {
        return entityManager.merge(curr);
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
    @SuppressWarnings("unchecked")
    public List<Currency> getAllCurrenciesByCourseDate(Date date) {
        return entityManager.createQuery("select  c from Currency  c where c.course.date = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Currency> getAllCurrenciesByCourseName(Name name) {
        return entityManager.createQuery("select  c from Currency  c where c.course.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public boolean isExist(Currency curr) {
        return !entityManager.createQuery("select count(c) from Currency c")
                .getSingleResult()
                .equals(0L);
    }

    @Override
    public void delete(Currency curr) {
        entityManager.remove(curr);
    }
}
