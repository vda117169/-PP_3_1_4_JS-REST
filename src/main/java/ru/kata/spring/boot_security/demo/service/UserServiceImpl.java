package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public void createUser(User user, String[] roles) {
        addAndCreate(user, roles);
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user, String[] roles) {
        addAndCreate(user, roles);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String role) {
        return roleRepository.findByRoleName(role);
    }

    private void addAndCreate(User user,
                              String[] roles){
        String roleName = null;
        for (String s : roles) {
            roleName = s;
        }
        Role role = roleRepository.findByRoleName(roleName);
        List<Role> roleList2 = new ArrayList<>();
        roleList2.add(role);

        user.setRoles(roleList2);

        userRepository.save(user);

    }
}
