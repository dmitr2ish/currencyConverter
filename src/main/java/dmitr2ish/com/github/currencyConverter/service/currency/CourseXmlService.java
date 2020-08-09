package dmitr2ish.com.github.currencyConverter.service.currency;

import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.CourseXml;

import java.text.ParseException;
import java.util.List;

public interface CourseXmlService {
    void saveCourse(CourseXml curseXml, List<CurrencyXml> currensyXmlList);
}
