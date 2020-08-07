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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public Currency getById(Long id) {
        return currencyRepository.getById(id);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.getAllCurrencies();
    }

    @Override
    public List<Currency> getAllCurrenciesByCourseDate(Date date) {
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
    public List<Course> getByDate(Date date) {
        return courseRepository.getByDate(date);
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
    public void saveCourse(CourseXml curseXml, List<CurrencyXml> currencyXmlList) {
        Course curse = new Course();    //currencyList null; id null; name null; date null;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            curse.setDate(format.parse(curseXml.getDate()));    //currencyList null; id null; name null; date have;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        curse.setName(curseXml.getName());  //currencyList null; id null; name have; date have;
        courseRepository.add(curse);    //currencyList null; id have; name have; date have;

        for (CurrencyXml currencyXml : currencyXmlList) {
            Currency currency = new Currency();
            currency.setId(currencyXml.getId());    //get id
            currency.setCharCode(currencyXml.getCharCode()); //get charCode
            currency.setName(currencyXml.getName());    //get name
            currency.setNumCode(currencyXml.getNumCode());  //get numCode
            currency.setNominal(currencyXml.getNominal());  //get nominal
            currency.setValue(currencyXml.getValue());  //get value
            currency.setCourse(courseRepository.getByName(curse.getName()));    //get course, where currenscyList null
            currencyRepository.addCurrency(currency);
        }
        curse.setCurrencyList(currencyRepository.getAllCurrenciesByCourseDate(curse.getDate()));
    }
}
