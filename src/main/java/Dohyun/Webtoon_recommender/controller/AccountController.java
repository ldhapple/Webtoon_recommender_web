package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "account/register";
    }


    @PostMapping("/register")
    public String register(User user)
    {
        userService.save(user);
        return "redirect:/account/login";
    }
}
