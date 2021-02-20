package ru.itis.spring_boot_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.spring_boot_demo.models.User;

public interface UsersRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
