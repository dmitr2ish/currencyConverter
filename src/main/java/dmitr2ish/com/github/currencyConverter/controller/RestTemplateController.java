package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.ValCurseXml;
import dmitr2ish.com.github.currencyConverter.service.currencyXml.CurrensyXmlService;
import dmitr2ish.com.github.currencyConverter.service.currencyXml.ValCurseXmlService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/admin")
public class RestTemplateController {

    final private  RestTemplate restTemplate;
    final private  CurrensyXmlService currensyXmlService;
    final private ValCurseXmlService valCurseXmlService;


    private final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    @Autowired
    public RestTemplateController(RestTemplate restTemplate, CurrensyXmlService currensyXmlService, ValCurseXmlService valCurseXmlService) {
        this.restTemplate = restTemplate;
        this.currensyXmlService = currensyXmlService;
        this.valCurseXmlService = valCurseXmlService;
    }

    @GetMapping(value = "/rest/currencies")
    public void getCurrencies() throws IOException {
        RestTemplate restTemplate = new RestTemplate();


        ValCurseXml valCurseXml = restTemplate.getForObject(URL, ValCurseXml.class);


        String url = "http://www.cbr.ru/scripts/XML_daily.asp";
        Document page = Jsoup.connect(url).get();
        Elements valCurseList = page.children();
        Elements currencyList = valCurseList.get(0).children();
        List<CurrencyXml> currencyXmlList = new ArrayList<>();
        for(Element i : currencyList) {
            CurrencyXml currencyXml = new CurrencyXml();
            currencyXml.setId(i.attr("ID"));
            currencyXml.setNumCode(Long.parseLong(i.childNode(0).childNode(0).toString().trim()));
            currencyXml.setCharCode((i.childNode(1).childNode(0).toString().trim()));
            currencyXml.setNominal(Integer.parseInt(i.childNode(2).childNode(0).toString().trim()));
            currencyXml.setName((i.childNode(3).childNode(0).toString().trim()));
            currencyXml.setValue(bigDecimalConvert(i.childNode(4).childNode(0).toString().trim()));

            currencyXmlList.add(currencyXml);
        }



    }

    public BigDecimal bigDecimalConvert(String number) {
        return new BigDecimal(number.replaceAll(",", "."));
    }
}
