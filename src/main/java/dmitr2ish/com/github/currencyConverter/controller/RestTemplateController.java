package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.CourseXml;
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
import java.util.*;


@RestController
@RequestMapping("/admin")
public class RestTemplateController {

    final private RestTemplate restTemplate;
    final private CourseXmlService courseXmlService;

    final private String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Autowired
    public RestTemplateController(RestTemplate restTemplate, CourseXmlService courseXmlService) {
        this.restTemplate = restTemplate;
        this.courseXmlService = courseXmlService;
    }

    @GetMapping(value = "/rest/currencies")
    public void getCurrencies() throws IOException, ParseException {
        RestTemplate restTemplate = new RestTemplate();
        CourseXml courseXml = restTemplate.getForObject(URL, CourseXml.class);

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

        courseXmlService.saveCourse(courseXml, currencyXmlList);
    }

    public BigDecimal bigDecimalConvert(String number) {
        return new BigDecimal(number.replaceAll(",", "."));
    }
}
