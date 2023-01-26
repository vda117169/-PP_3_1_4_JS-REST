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
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping()
public class AdminController {

    private  final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, @AuthenticationPrincipal User currentUser) {
        User newUser = new User();
        model.addAttribute("allUs", userService.showAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("rolesList", userService.getAllRoles());
        model.addAttribute("newUser", newUser);
        return "admin";
    }

    @GetMapping("/user")
    public String showInfoForUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("currentUser", user);
        return "info_for_user";
    }

    @GetMapping("/add")
    public String getUser() { //заполнение
        return "add";
    }

    @PostMapping("/add")
    public String userAdditions(@ModelAttribute() User user,
                                @RequestParam() String[] role) {
        userService.createUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String updateListAllUsers(@ModelAttribute() User user,
                                     @RequestParam() String[] role) {
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
