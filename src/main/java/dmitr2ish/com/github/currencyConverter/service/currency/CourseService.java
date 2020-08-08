package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    Course getByName(String name);

    Course getByDate(LocalDate date);

    Course getCourseById(Long id);

    List<Course> getAll();

    void add(Course curse);

    boolean isExistByDate(LocalDate date);

    boolean isEmpty();
}
