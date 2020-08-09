package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.currency.Course;
import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import dmitr2ish.com.github.currencyConverter.performanceXml.CourseXml;
import dmitr2ish.com.github.currencyConverter.performanceXml.CurrencyXml;
import dmitr2ish.com.github.currencyConverter.service.currency.CourseService;
import dmitr2ish.com.github.currencyConverter.service.currency.CourseXmlService;
import dmitr2ish.com.github.currencyConverter.service.user.RoleService;
import dmitr2ish.com.github.currencyConverter.service.user.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MainController {
    final private UserService userService;
    final private RoleService roleService;
    final private CourseService courseService;
    final private RestTemplate restTemplate;
    final private CourseXmlService courseXmlService;

    private static Collection<DayOfWeek> weekends = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private static Collection<LocalDate> holidays = new HashSet<>();

    final private String URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    final private DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");

    @Autowired
    public MainController(UserService userService, RoleService roleService, CourseService courseService, RestTemplate restTemplate, CourseXmlService courseXmlService) {
        this.userService = userService;
        this.roleService = roleService;
        this.courseService = courseService;
        this.restTemplate = restTemplate;
        this.courseXmlService = courseXmlService;
    }

    @GetMapping(value = "/bdinit")
    public String secretPage() {
        roleService.add(new Role("ADMIN"));
        roleService.add(new Role("USER"));
        User user = new User();
        user.setLogin("test@test.ru");
        user.setPassword("test");
        user.setRoles(roleService.getAll());
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    //getting view and if we need actual course doing request to market
    @GetMapping(value = "/")
    public ModelAndView mainPage(Authentication authentication) {
        RestTemplate restTemplate = new RestTemplate();

        //getting actual course whiteout currencies
        CourseXml courseXml = restTemplate.getForObject(URL, CourseXml.class);

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(courseXml.getDate(), format);

        //if today is not weekand and holiday, update now date
        if (!isHoliday(LocalDate.now())) {
            /* I specifically enter one date in local time and another by request from the exchange,
             * if there is no date on the exchange, I will have my own date
             */
            localDate = LocalDate.now();
            format.format(localDate);
        }

        //if data base is empty or if db isn't exist this course we get course and currencies, else ignoring the request
        if (courseService.isEmpty() || !courseService.isExistByDate(localDate)) {

            //getting actual currencies in this course
            Document page = null;
            try {
                page = Jsoup.connect(URL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        }

        Course course = courseService.getByDate(localDate);

        ModelAndView mav = new ModelAndView("main")
                .addObject("authUser", authentication.getPrincipal());
        return mav.addObject("currentCourse", course);
    }

    //simple bigDecimal convert
    public BigDecimal bigDecimalConvert(String number) {
        return new BigDecimal(number.replaceAll(",", "."));
    }

    //if will a some holiday
    public static void addHoliday(LocalDate date) {
        holidays.add(date);
    }

    //additional checking if holiday or weekand
    public static boolean isHoliday(LocalDate date) {
        return (weekends.contains(date.getDayOfWeek()) || holidays.contains(date));
    }
}
