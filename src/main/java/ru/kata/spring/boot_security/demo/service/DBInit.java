package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit {

    private final UserService userService;

    private final RoleService roleService;

    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
       Role roleAdmin = new Role (1L, "ROLE_ADMIN");
       Role roleUser = new Role (2L, "ROLE_USER");
       roleService.add(roleAdmin);
        roleService.add(roleUser);

        List<Role> setAdmin = new ArrayList<>();
        String[] listRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};

        setAdmin.add(roleAdmin);
        setAdmin.add(roleUser);
        User admin = new User();
        admin.setAge(22);
        admin.setRoles(setAdmin);
        admin.setName("Daria");
        admin.setEmail("123@mail.ru");
        admin.setPassword("123");
        admin.setLastName("Voitenko");

        userService.createUser(admin, listRoles);
    }
}
