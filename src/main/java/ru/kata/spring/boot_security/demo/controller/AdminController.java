package ru.kata.spring.boot_security.demo.controller;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class AdminController {

    private  final UserService userService;
    private  final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.showAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("/user")
//    public String showInfoForUser(Model model, Principal principal) {
//        User user = userService.getUserByName(principal.getName());
//        model.addAttribute("home_page", user );
//        return "home_page";
//    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    @SneakyThrows
    @PostMapping()
    public ResponseEntity<HttpStatus> userAdditions(@Valid @RequestBody User user) {
       userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody User user,
                             @PathVariable("id") Long id) {
        userService.updateUser(user, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
