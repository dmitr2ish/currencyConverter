package dmitr2ish.com.github.currencyConverter.service.user;

import dmitr2ish.com.github.currencyConverter.entity.user.Role;
import dmitr2ish.com.github.currencyConverter.entity.user.User;
import dmitr2ish.com.github.currencyConverter.repository.user.RoleRepository;
import dmitr2ish.com.github.currencyConverter.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, RoleService {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    final private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void addUser(User user) {
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        userRepository.addUser(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean isExist(User user) {
        return userRepository.isExist(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public void add(Role role) {
        roleRepository.add(role);
    }
}
