package ru.kata.spring.boot_security.demo.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class UsersRestController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UsersRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    //Get all users
    @GetMapping("admin/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Create a new user
    @SneakyThrows
    @PostMapping("admin")
    public ResponseEntity<User> newUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO);
        Set<Role> roles = new HashSet<>();
        for (String roleName : userDTO.getRoleNames()) {
            roles.add(roleService.getByName(roleName));
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //GET all roles
    @GetMapping("admin/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }


    //Getting a user by Id
    @GetMapping("admin/{id}")
    public ResponseEntity<User> getUserId(@PathVariable(name = "id") long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //User update
    @SneakyThrows
    @PutMapping("admin")
    public ResponseEntity<User> editUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO);
        Set<Role> roles = new HashSet<>();
        for (String roleName : userDTO.getRoleNames()) {
            roles.add(roleService.getByName(roleName));
        }
        user.setRoles(roles);
        userService.edit(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete user by ID
    @DeleteMapping("admin/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}