package dmitr2ish.com.github.currencyConverter.service.security;

import dmitr2ish.com.github.currencyConverter.entity.user.User;
import dmitr2ish.com.github.currencyConverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("A user with this login " + login + " was not found");
        }
        return user;
    }
}
