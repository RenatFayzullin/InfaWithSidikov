import java.util.List;
import java.util.Optional;

public interface CrudRepository <T>{
    List<T> findAll();
    Optional<T> findById(Long id);

    void save (T entire);
    void update (T entire);
    void remove (T entire);
    void removeById (Long id);
}
