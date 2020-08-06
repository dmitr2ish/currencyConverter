package dmitr2ish.com.github.currencyConverter.controller;

import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import dmitr2ish.com.github.currencyConverter.service.user.RoleService;
import dmitr2ish.com.github.currencyConverter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    final private UserService userService;
    final private RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
}
