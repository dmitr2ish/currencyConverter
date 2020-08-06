package dmitr2ish.com.github.currencyConverter.config.security.handler;

/* Logic for successful authentication
 * */

import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();

        boolean adminflag = false;
        boolean userflag = false;

        for (Role i : user.getRoles()) {
            if (!adminflag) {
                adminflag = (i.getName().equals("ADMIN"));
            }
            if (!userflag) {
                userflag = (i.getName().equals("USER"));
            }
        }

        if (adminflag) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (userflag) {
            httpServletResponse.sendRedirect("/user");
        } else {
            httpServletResponse.sendRedirect("/error");
        }
    }
}
