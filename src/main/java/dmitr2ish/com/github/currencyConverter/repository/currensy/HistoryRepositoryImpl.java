package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    final private EntityManager entityManager;

    @Autowired
    public HistoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addHistory(History history) {
        entityManager.persist(history);
    }

    @Override
    public History update(History history) {
        return entityManager.merge(history);
    }

    @Override
    public History getById(Long id) {
        return entityManager.find(History.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<History> getAllHistories(String login) {
        return entityManager.createQuery("select c from History c where c.login = :login")
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<History> getAllHistoriesByDate(LocalDate date, String login) {
        return entityManager.createQuery("select c from History c where c.date = :date and c.login = :login")
                .setParameter("date", date)
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<History> getAllHistoriesByDateAndCurrencies(LocalDate date,
                                                            String initialNameCurrency,
                                                            String targetNameCurrency,
                                                            String login) {
        String sqlQuery
                = "select c from History c where " +
                "c.date = :date and " +
                "c.initialCharCodeCurrency = :iname and " +
                "c.targetCharCodeCurrency = :tname and " +
                "c.login = :login";

        return entityManager.createQuery(sqlQuery)
                .setParameter("date", date)
                .setParameter("iname", initialNameCurrency)
                .setParameter("tname", targetNameCurrency)
                .setParameter("login", login)
                .getResultList();
    }

    @Override
    public boolean isExist(History history) {
        return !entityManager.createQuery("select count(c) from Currency c")
                .getSingleResult()
                .equals(0L);
    }

    @Override
    public void delete(History history) {
        entityManager.remove(history);
    }

    @Override
    public void deleteAll(String login) {
        entityManager.createQuery("delete from History c where c.login = :login")
                .setParameter("login", login)
                .executeUpdate();
    }
}
