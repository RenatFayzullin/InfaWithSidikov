package ru.itis.spring_boot_demo.services;

import ru.itis.spring_boot_demo.dto.UserForm;

public interface SignUpService {
    void signUp(UserForm form);
}
