package web.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.SpringSecurity.service.UserService;


@Controller
public class UsersController {
    private final UserService service;


    @Autowired
    public UsersController(UserService service) {
        this.service = service;

    }

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", service.userById(id));
        return "user";
    }
}
