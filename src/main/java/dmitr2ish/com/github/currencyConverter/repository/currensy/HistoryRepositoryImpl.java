package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
    public List<History> getAllHistories() {
        return entityManager.createQuery("select c from History c").getResultList();
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
}
