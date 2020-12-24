package ru.itis.repositories.users;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.itis.dto.UserDto;
import ru.itis.models.User;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    private ResultSetExtractor<Optional<User>> resultSetExtractor = resultSet -> {
        if (resultSet.next())
            return Optional.of(User.builder()
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .id(resultSet.getInt("id"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .email(resultSet.getString("email"))
                    .build());
            return Optional.empty();
    };

    //language=SQL
    private final static String SQL_INSERT = "insert into semest_user(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final static String SQL_FIND_BY_EMAIL = "select * from semest_user where email = ?";

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jdbcTemplate.query(
                SQL_FIND_BY_EMAIL,
                new Object[]{email},
                resultSetExtractor
        );
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(
                SQL_INSERT,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getHashPassword()
        );
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(User entity) {

    }

}
