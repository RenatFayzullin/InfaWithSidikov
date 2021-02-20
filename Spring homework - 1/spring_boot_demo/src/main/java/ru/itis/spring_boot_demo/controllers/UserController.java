package ru.itis.spring_boot_demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring_boot_demo.dto.UserDto;
import ru.itis.spring_boot_demo.services.UsersService;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public  String getUserPage(Model model){
        model.addAttribute("usersList",usersService.getAllUsers());
        return "users_pages";
    }

    @GetMapping("/users/{user-id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(usersService.getUserById(userId));
    }
}
