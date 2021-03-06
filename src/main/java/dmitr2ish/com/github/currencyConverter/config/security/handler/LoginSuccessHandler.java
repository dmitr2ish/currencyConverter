package dmitr2ish.com.github.currencyConverter.config.security.handler;

import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// Logic for successful authentication

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
            httpServletResponse.sendRedirect("/");
        } else if (userflag) {
            httpServletResponse.sendRedirect("/");
        } else {
            httpServletResponse.sendRedirect("/error");
        }
    }
}
