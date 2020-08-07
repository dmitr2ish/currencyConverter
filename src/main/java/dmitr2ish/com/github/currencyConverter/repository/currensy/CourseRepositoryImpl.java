package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    final private EntityManager entityManager;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void add(Course curse) {
        entityManager.persist(curse);
    }

    @Override
    public boolean isExistByDate(Date date) {
        return !entityManager.createQuery("select count(c) from Course c where c.date = :date")
                .setParameter("date", date)
                .getSingleResult()
                .equals(0L);
    }

    @Override
    public Course getByName(String name) {
        return (Course) entityManager.createQuery("select c from Course c where c.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> getByDate(Date date) {
        return entityManager.createQuery("select c from Course c where c.date = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> getAll() {
        return entityManager.createQuery("select  c from Course c").getResultList();
    }
}
