package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


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
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.showAllUsers());
        model.addAttribute("userLog", userService.getUserByName(principal.getName()));
        return "home_page_admin";
    }

    @GetMapping("/user")
    public String showInfoForUser(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("home_page", user );
        return "home_page";
    }

    @GetMapping("/new_user")
    public String getUser(@ModelAttribute() Model model, Principal principal) { //заполнение
        model.addAttribute("user", new User());
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("us", user);
        model.addAttribute("roles", roleService.findAll());
        return "home_page_admin/new_user";
    }

    @PostMapping(value = "/new_user")
    public String userAdditions(@ModelAttribute() User user,
                                @RequestParam(value = "newRole") String[] role) {
        userService.createUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}/edit")
    public String updateUser(@ModelAttribute("edit") User user,
                             @PathVariable("id") Long id,
                                     @RequestParam(value = "newRole") String[] role) throws Exception {
        userService.updateUser(user, role, id);
        return "redirect:/admin";
    }

    @DeleteMapping ("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
