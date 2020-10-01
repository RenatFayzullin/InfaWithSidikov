import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "100878ub$$";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
//    language=SQL
    public static final String SQL_SELECT_ALL_FROM_USER = "select * from users";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from users where last_name = 'Fayzillin'");
//
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("last_name"));
//        }

        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        List<User> a = usersRepositoryJdbc.findAllAge(27);

        for (int i = 0; i<a.size();i++){
            System.out.println(a.get(i).getId()+" "+ a.get(i).getLastName()+" "+a.get(i).getFirstName()+" "+a.get(i).getAge());
        }

        a = usersRepositoryJdbc.findAllByLastName("Khanannov");
        for (int i = 0; i<a.size();i++){
            System.out.println(a.get(i).getId()+" "+ a.get(i).getLastName()+" "+a.get(i).getFirstName()+" "+a.get(i).getAge());
        }



//        while(resultSet.next()) {
//            System.out.print(resultSet.getInt("id")+" ");
//            System.out.print(resultSet.getString("first_name")+" ");
//            System.out.print(resultSet.getString("last_name")+" ");
//            System.out.println(resultSet.getInt("age")+" ");
//        }


    }
}
