import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl {

    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_USERS = "select * from users";
    private static final String SQL_SELECT_AGE_FROM_USERS = "select * from users where age = ";
    private static final String SQL_SELECT_LAST_NAME_FROM_USERS = "select * from users where last_name = ";


    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAllAge(Integer age) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_AGE_FROM_USERS + age);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }


    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_USERS);

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<User> findAllByLastName(String lastName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LAST_NAME_FROM_USERS + "'" +lastName+ "'");

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"));
                result.add(user);
            }
            return result;


        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }


    }


    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    public void save(User entity) {

    }

    public void update(User entity) {

    }

    public void remove(User entity) {

    }

    public void removeById(Long id) {

    }


}
