package ru.itis.spring_boot_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.spring_boot_demo.dto.UserForm;
import ru.itis.spring_boot_demo.services.SignInService;


@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/signIn")
    public String getSignInPage(){
        return "sign_in_page";
    }

    @PostMapping("/signIn")
    public String signIn(UserForm form){

        if (signInService.signIn(form).isPresent()){
            return "redirect:/signIn";
        }
        else {
            return "redirect:/users";
        }

    }
}
