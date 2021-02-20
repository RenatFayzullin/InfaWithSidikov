package ru.itis.spring_boot_demo.dto;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
