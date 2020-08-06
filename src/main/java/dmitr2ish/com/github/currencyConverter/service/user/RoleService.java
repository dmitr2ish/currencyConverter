package dmitr2ish.com.github.currencyConverter.service.user;

import dmitr2ish.com.github.currencyConverter.entity.user.Role;

import java.util.List;

public interface RoleService {
    Role getByName(String name);

    List<Role> getAll();

    void add(Role role);
}
