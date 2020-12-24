package ru.itis.repositories.users;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);
    Optional<User> findUserByEmail(String login);
}
