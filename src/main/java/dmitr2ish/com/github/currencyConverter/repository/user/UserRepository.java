package dmitr2ish.com.github.currencyConverter.repository.user;

import dmitr2ish.com.github.currencyConverter.entity.user.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);

    void update(User user);

    User getById(Long id);

    User getByLogin(String login);

    List<User> getAllUsers();

    boolean isExist(User user);

    void delete(User user);
}
