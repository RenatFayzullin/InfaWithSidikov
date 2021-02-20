package ru.itis.spring_boot_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.spring_boot_demo.dto.UserDto;
import ru.itis.spring_boot_demo.models.User;
import ru.itis.spring_boot_demo.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return UserDto.from(users);
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return UserDto.from(user.orElse(User.builder().build()));
    }
}
