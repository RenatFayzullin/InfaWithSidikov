package ru.itis.spring_boot_demo.services;

import ru.itis.spring_boot_demo.dto.UserDto;
import ru.itis.spring_boot_demo.dto.UserForm;
import ru.itis.spring_boot_demo.models.User;

import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(UserForm form);
}
