package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

public class DbInit {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));

        String[] rolesAdmin = {"ROLE_ADMIN", "ROLE_USER"};
        userService.save(new User("admin", "admin", 18, "admin@mail.com"),
                rolesAdmin, "admin");

        String[] rolesUser = {"ROLE_USER"};
        userService.save(new User("user", "user", 18, "user@mail.com"),
                rolesUser, "user");
    }
}
