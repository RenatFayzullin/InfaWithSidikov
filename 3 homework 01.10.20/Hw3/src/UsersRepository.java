import java.util.List;

public interface UsersRepository {
    List<User> findAllByAge(Integer age);
}
