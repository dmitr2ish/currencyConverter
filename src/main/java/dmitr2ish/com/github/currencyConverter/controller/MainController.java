package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.currency.Course;
import dmitr2ish.com.github.currencyConverter.entity.currency.Currency;
import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import dmitr2ish.com.github.currencyConverter.service.currency.CourseService;
import dmitr2ish.com.github.currencyConverter.service.currency.CurrencyService;
import dmitr2ish.com.github.currencyConverter.service.user.RoleService;
import dmitr2ish.com.github.currencyConverter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {
    final private UserService userService;
    final private RoleService roleService;
    final private CourseService courseService;

    @Autowired
    public MainController(UserService userService, RoleService roleService, CourseService courseService) {
        this.userService = userService;
        this.roleService = roleService;
        this.courseService = courseService;
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

    @GetMapping(value = "/")
    public ModelAndView mainPage(Authentication authentication) {
        if (courseService.isEmpty()) {
            //TODO делаем запрос
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate ldt = LocalDate.now();
        format.format(ldt);


        Course course = courseService.getByDate(ldt);
        ModelAndView mav = new ModelAndView("main")
                .addObject("authUser", authentication.getPrincipal());
        return mav.addObject("currentCourse", course);
    }
}
