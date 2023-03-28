package ru.kata.spring.boot_security.demo.service;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public void createUser(User user) {

        if (!user.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            userRepository.save(user);
        }
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public void updateUser(User user, Long id)  {

       User userForUpdate = userRepository.findById(id).orElseThrow(() -> new Exception("User not found on :: " + id));

        if (!user.getPassword().isEmpty()) {
            userForUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        if (user.getId() != null) {
            userForUpdate.setId(user.getId());
        }
        if (user.getName() != null) {
            userForUpdate.setName(user.getName());
        }
        if (user.getAge() != null ) {
            userForUpdate.setAge(user.getAge());
        }
        if (user.getEmail() != null) {
            userForUpdate.setEmail(user.getEmail());
        }
       if (user.getLastName() != null) {
           userForUpdate.setLastName(user.getLastName());
        }
        userRepository.save(userForUpdate);
    }


    private User setUserValue(User user, String[] roles) {
        String roleName;
        Set<Role> roleList2 = new HashSet<>() {
        };
        for (String s : roles) {
            roleName = s;
            Role role = roleRepository.findByRoleName(roleName);

            roleList2.add(role);
            user.setRoles(roleList2);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

}
