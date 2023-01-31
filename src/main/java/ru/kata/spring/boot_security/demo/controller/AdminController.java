package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping()
public class AdminController {

    private  final UserService userService;
    private  final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("home_page_admin", userService.showAllUsers());
        return "home_page_admin";
    }

    @GetMapping("/user")
    public String showInfoForUser(Model model) {
        model.addAttribute("home_page", userService.showAllUsers());
        return "home_page";
    }

    @GetMapping("/add")
    public String getUser() { //заполнение
        return "add";
    }

    @PostMapping("/add")
    public String userAdditions(@ModelAttribute() User user,
                                @RequestParam(value = "newRole") String[] role) {
        userService.createUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("edit", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateListAllUsers(@ModelAttribute() User user,
                                     @RequestParam() String[] role) {
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
