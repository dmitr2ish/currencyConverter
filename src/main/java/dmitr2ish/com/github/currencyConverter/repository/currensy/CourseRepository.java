package dmitr2ish.com.github.currencyConverter.repository.currensy;

import dmitr2ish.com.github.currencyConverter.entity.currency.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository {

    Course getByName(String name);

    Course getByDate(LocalDate date);

    Course getById(Long id);

    List<Course> getAll();

    void add(Course curse);

    boolean isExistByDate(LocalDate date);

    boolean isEmpty();
}
