package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    void createUser(User user, String[] roles);

    void deleteUser(Long id);

    void updateUser(User user, String[] roles);

    User getUserById(Long id);

    User getUserByName(String name);

    List<User> showAllUsers();

}
