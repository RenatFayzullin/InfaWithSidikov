package ru.itis.spring_boot_demo.services;

import ru.itis.spring_boot_demo.dto.UserDto;

import java.util.List;

public interface UsersService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);
}
