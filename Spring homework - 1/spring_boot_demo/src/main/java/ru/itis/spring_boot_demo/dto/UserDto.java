package ru.itis.spring_boot_demo.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.spring_boot_demo.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;

    public static UserDto from (User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public static List<UserDto> from (List<User> users){
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
