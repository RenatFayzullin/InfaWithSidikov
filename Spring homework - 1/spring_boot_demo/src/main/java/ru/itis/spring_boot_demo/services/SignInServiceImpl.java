package ru.itis.spring_boot_demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.spring_boot_demo.dto.UserDto;
import ru.itis.spring_boot_demo.dto.UserForm;
import ru.itis.spring_boot_demo.models.User;
import ru.itis.spring_boot_demo.repositories.UsersRepository;

import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDto> signIn(UserForm form) {
        Optional<User> user = Optional.ofNullable(usersRepository.findByEmail(form.getEmail()));
        if (user.isPresent()){
            if (passwordEncoder.matches(form.getPassword(),user.get().getPassword())){
                return Optional.of(user.get().toUserDto());
            }
        }
        return Optional.empty();
    }
}
