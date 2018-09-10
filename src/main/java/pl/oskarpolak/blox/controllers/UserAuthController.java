package pl.oskarpolak.blox.controllers;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.blox.models.PostEntity;
import pl.oskarpolak.blox.models.UserEntity;
import pl.oskarpolak.blox.models.forms.CommentForm;
import pl.oskarpolak.blox.models.forms.LoginForm;
import pl.oskarpolak.blox.models.forms.RegisterForm;
import pl.oskarpolak.blox.models.repositories.UserRepository;
import pl.oskarpolak.blox.models.services.UserService;

import java.util.Optional;

@Controller
public class UserAuthController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm,
                           Model model) {
        if (userService.isUserExistByLogin(registerForm.getLogin())) {
            model.addAttribute("info", "Ten login jest już zajęty");
            return "register";
        }
        userService.registerUser(registerForm);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        Model model) {
        if (!userService.loginUser(loginForm.getLogin(), loginForm.getPassword())) {
            model.addAttribute("info", "Błędne dane logowania");
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOut() {

        userService.logOut();
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String showUser(Model model) {
        UserEntity userEntity;

        if (userService.isLogin()) {
            userEntity = userService.getUserData();

            model.addAttribute("user", userEntity);

        }
        return "myprofile";
//to jest zwracany template
    }

}
