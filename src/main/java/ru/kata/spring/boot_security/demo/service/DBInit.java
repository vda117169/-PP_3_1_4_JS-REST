package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Component
//public class DBInit {
//
//    private final UserService userService;
//
//    private final RoleService roleService;
//
//    public DBInit(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    //@PostConstruct
//    public void init() {
//        List <User> users = new ArrayList<>();
//
//       Role roleAdmin = new Role (1L, "ROLE_ADMIN", users);
//       Role roleUser = new Role (2L, "ROLE_USER", users);
//       roleService.add(roleAdmin);
//        roleService.add(roleUser);
//
//        Set<Role> setAdmin = new HashSet<>();
//        String[] listRoles = new String[] {"ROLE_USER", "ROLE_ADMIN"};
//
//        setAdmin.add(roleAdmin);
//        setAdmin.add(roleUser);
//        User admin = new User();
//        admin.setAge(22L);
//        admin.setRoles(setAdmin);
//        admin.setName("Daria");
//        admin.setEmail("123@mail.ru");
//        admin.setPassword("123");
//        admin.setLastName("Voitenko");
//        users.add(admin);
//
//        userService.createUser(admin);
//    }
//}
