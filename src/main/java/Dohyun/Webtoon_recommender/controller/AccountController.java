package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.Validator.CheckUsernameValidator;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/account")
public class AccountController {

    private final CheckUsernameValidator checkUsernameValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkUsernameValidator);
    }

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "account/register";
    }

//
    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult,  Model model, Errors errors)
    {
        if(errors.hasErrors()){
            model.addAttribute("userDto",user);

            Map<String, String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            return "account/register";
        }
        else{
            userService.save(user);
            return "redirect:/account/login";
        }

    }


}
