package ru.itis.services.users;

import ru.itis.dto.UserDto;

import java.util.Optional;

public interface UsersService {
    Optional<UserDto> getUserByCookie(String value);
}
