package ru.itis.repositories;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into semest_user(first_name, last_name, email, hash_password) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private final static String SQL_FIND_BIND_EMAIL = "select * from semest_user where email = ";

    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Connection connection = null;
        Statement statement = null;
        ResultSet userFromDB = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            userFromDB = statement.executeQuery(SQL_FIND_BIND_EMAIL+"'"+email+"'");


            if (userFromDB.next()) {
                return Optional.of(User.builder()
                        .email(userFromDB.getString("email"))
                        .hashPassword(userFromDB.getString("hash_password"))
                        .build());
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        finally {
            if (userFromDB != null){
                try{
                    userFromDB.close();
                } catch (SQLException ignored){
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getHashPassword());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));
            } else {
                throw new SQLException("Problem with retrieve id");
            }


        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            closeAllConnections(generatedKeys,statement,connection);
        }

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

    private void closeAllConnections(ResultSet resultSet, PreparedStatement statement, Connection connection) {


        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }


    }
}
