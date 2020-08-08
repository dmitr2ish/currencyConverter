package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.entity.currency.Course;
import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.CourseXml;
import dmitr2ish.com.github.currencyConverter.repository.currensy.CurrencyRepository;
import dmitr2ish.com.github.currencyConverter.repository.currensy.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.Name;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImplement implements CurrencyService, CourseService, CourseXmlService {
    final private CurrencyRepository currencyRepository;
    final private CourseRepository courseRepository;


    @Autowired
    public CurrencyServiceImplement(CurrencyRepository currencyRepository, CourseRepository courseRepository) {
        this.currencyRepository = currencyRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void addCurrency(Currency curr) {
        currencyRepository.addCurrency(curr);
    }

    @Override
    public void update(Currency curr) {
        currencyRepository.update(curr);
    }

    @Override
    public Currency getById(String id) {
        return currencyRepository.getById(id);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.getAllCurrencies();
    }

    @Override
    public List<Currency> getAllCurrenciesByCourseDate(LocalDate date) {
        return currencyRepository.getAllCurrenciesByCourseDate(date);
    }

    @Override
    public List<Currency> getAllCurrenciesByCourseName(Name name) {
        return currencyRepository.getAllCurrenciesByCourseName(name);
    }

    @Override
    public boolean isExist(Currency curr) {
        return currencyRepository.isExist(curr);
    }

    @Override
    public void delete(Currency curr) {
        currencyRepository.delete(curr);
    }

    @Override
    public Course getByName(String name) {
        return courseRepository.getByName(name);
    }

    @Override
    public Course getByDate(LocalDate date) {
        return courseRepository.getByDate(date);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    @Override
    public void add(Course curse) {
        courseRepository.add(curse);
    }

    @Override
    public boolean isExistByDate(LocalDate date) {
        return courseRepository.isExistByDate(date);
    }

    @Override
    public boolean isEmpty() {
        return courseRepository.isEmpty();
    }

    @Override
    public void saveCourse(CourseXml courseXml, List<CurrencyXml> currencyXmlList) {
        Course course = new Course();    //currencyList null; id null; name null; date null;

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
        //convert String to LocalDate and set to course
        course.setDate(LocalDate.parse(courseXml.getDate(), format));    //currencyList null; id null; name null; date have;

        course.setName(courseXml.getName());  //currencyList null; id null; name have; date have;
        courseRepository.add(course);    //currencyList null; id have; name have; date have;

        //added manually RUB currency
        Currency currencyRub = new Currency();
        currencyRub.setId("R00000");    //set id
        currencyRub.setCharCode("RUB "); //set charCode
        currencyRub.setName("Российский рубль");    //set name
        currencyRub.setNumCode(643L);  //set numCode
        currencyRub.setNominal(1);  //set nominal
        currencyRub.setValue(BigDecimal.valueOf(1));  //set value
        currencyRub.setCourse(courseRepository.getByName(course.getName()));    //get course, where currenscyList null
        currencyRepository.addCurrency(currencyRub);

        for (CurrencyXml currencyXml : currencyXmlList) {
            Currency currency = new Currency();
            currency.setId(currencyXml.getId());    //get id
            currency.setCharCode(currencyXml.getCharCode()); //get charCode
            currency.setName(currencyXml.getName());    //get name
            currency.setNumCode(currencyXml.getNumCode());  //get numCode
            currency.setNominal(currencyXml.getNominal());  //get nominal
            currency.setValue(currencyXml.getValue());  //get value
            currency.setCourse(courseRepository.getByName(course.getName()));    //get course, where currenscyList null
            currencyRepository.addCurrency(currency);
        }
        course.setCurrencyList(currencyRepository.getAllCurrenciesByCourseDate(course.getDate()));
    }
}
