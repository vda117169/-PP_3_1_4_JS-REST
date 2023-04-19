package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DBInit {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DBInit(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void postConstruct() {

        //Roles
        Role admin = new Role();
        admin.setId(1L);
        admin.setRole("ROLE_ADMIN");

        Role user = new Role();
        user.setId(2L);
        user.setRole("ROLE_USER");

        roleRepository.saveAll(List.of(admin, user));

        //Users
        User adminUser = new User();
        adminUser.setName("Daria");
        adminUser.setLastName("Voitenko");
        adminUser.setAge((byte) 35);
        adminUser.setEmail("123@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("123"));
        adminUser.addRole(admin);

        User defaultUser = new User();
        defaultUser.setName("Ivan");
        defaultUser.setLastName("Ivanov");
        defaultUser.setAge((byte) 40);
        defaultUser.setEmail("321@gmail.com");
        defaultUser.setPassword(passwordEncoder.encode("123"));
        defaultUser.addRole(user);

        userRepository.save(adminUser);
        userRepository.save(defaultUser);
    }
}
