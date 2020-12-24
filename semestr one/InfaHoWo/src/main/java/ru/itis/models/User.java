package ru.itis.models;

import lombok.*;
import ru.itis.dto.UserDto;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;

    public UserDto toUserDto() {
        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .id(id)
                .email(email)
                .build();
    }
}
