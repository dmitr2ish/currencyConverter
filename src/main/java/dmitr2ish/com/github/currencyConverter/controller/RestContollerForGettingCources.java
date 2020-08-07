package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.CourseXml;
import dmitr2ish.com.github.currencyConverter.service.currency.CourseService;
import dmitr2ish.com.github.currencyConverter.service.currency.CourseXmlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/admin")
public class RestContollerForGettingCources {

    final private RestTemplate restTemplate;
    final private CourseXmlService courseXmlService;
    final private CourseService courseService;

    final private String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Autowired
    public RestContollerForGettingCources(RestTemplate restTemplate,
                                          CourseXmlService courseXmlService,
                                          CourseService courseService) {
        this.restTemplate = restTemplate;
        this.courseXmlService = courseXmlService;
        this.courseService = courseService;
    }

    //getting actual course and currencies on today
    @GetMapping(value = "/rest/currencies")
    public void getTodayCourse() throws ParseException, IOException {
        RestTemplate restTemplate = new RestTemplate();

        //getting actual course whiteout currencies
        CourseXml courseXml = restTemplate.getForObject(URL, CourseXml.class);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date currentCourseDate = format.parse(courseXml.getDate());

        //if db isn't exist this course we get currencies, else ignoring the request
        if (!courseService.isExistByDate(currentCourseDate)) {
            //getting actual currencies in this course
            Document page = Jsoup.connect(URL).get();
            Elements valCurseList = page.children();
            Elements currencyList = valCurseList.get(0).children();
            List<CurrencyXml> currencyXmlList = new ArrayList<>();
            for (Element i : currencyList) {
                CurrencyXml currencyXml = new CurrencyXml();
                currencyXml.setId(i.attr("ID"));
                currencyXml.setNumCode(Long.parseLong(i.childNode(0).childNode(0).toString().trim()));
                currencyXml.setCharCode((i.childNode(1).childNode(0).toString().trim()));
                currencyXml.setNominal(Integer.parseInt(i.childNode(2).childNode(0).toString().trim()));
                currencyXml.setName((i.childNode(3).childNode(0).toString().trim()));
                currencyXml.setValue(bigDecimalConvert(i.childNode(4).childNode(0).toString().trim()));
                currencyXmlList.add(currencyXml);
            }
            //saving in data base
            courseXmlService.saveCourse(courseXml, currencyXmlList);
        } else {
            System.err.println("Course of this date already exist in data base");
        }
    }

    public BigDecimal bigDecimalConvert(String number) {
        return new BigDecimal(number.replaceAll(",", "."));
    }
}
