package pl.oskarpolak.blox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.oskarpolak.blox.models.services.PostService;
import pl.oskarpolak.blox.models.services.UserService;

@Controller
public class MainController{

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @ModelAttribute
    public void addModel(Model model){
        model.addAttribute("user", userService);
    }

    @GetMapping("/")
    public String index(Model model){
        if(userService.getUserData()== null){
            return "redirect:/login";
        }
        model.addAttribute("user", userService.getUserData());
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("info", userService.isLogin() ? "WITAJ!" : "Zaloguj się");
        return "index";
    }


}
