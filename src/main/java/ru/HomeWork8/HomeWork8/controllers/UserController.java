package ru.HomeWork8.HomeWork8.controllers;

import ru.HomeWork8.HomeWork8.model.User;
import ru.HomeWork8.HomeWork8.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    String getOne(@PathVariable("id") int id, Model model){
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }

}
