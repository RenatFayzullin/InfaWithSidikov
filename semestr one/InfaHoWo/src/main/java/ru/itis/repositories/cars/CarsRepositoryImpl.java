package ru.itis.repositories.cars;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.dto.CarDto;
import ru.itis.models.Car;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarsRepositoryImpl implements CarsRepository {

    //language=SQL
    private static final String SQL_GET_ALL = "select car.*, country_brand.country from car join country_brand on car.id_country = country_brand.id;";
    //language=SQL
    private final static String SQL_GET_BY_ID = "select car.*, country_brand.country from car join country_brand on car.id_country = country_brand.id where car.id = ?";
    //language=SQL
    private final static String SQL_GET_COUNTRY = "select id from country_brand where country = ?";
    //language=SQL
    private final static String SQL_SEARCH = "select car.*, country_brand.country from car join country_brand on car.id_country = country_brand.id where title like";
    //language=SQL
    private final static String SQL_INSERT = "insert into car(title, description, price, model,mark_logo_id,id_country) " +
            "values (?, ?, ?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_GET_MARK_ID = "select distinct mark_logo_id from car where title = ?";

    private final RowMapper<CarDto> rowMapper = (resultSet, i) -> CarDto.builder()
            .id(resultSet.getInt("id"))
            .description(resultSet.getString("description"))
            .model(resultSet.getString("model"))
            .price(resultSet.getInt("price"))
            .title(resultSet.getString("title"))
            .markLogoId(resultSet.getInt("mark_logo_id"))
            .country(resultSet.getString("country"))
            .build();

    private final ResultSetExtractor<CarDto> resultSetExtractor = resultSet -> {
        if (resultSet.next())
            return CarDto.builder()
                    .id(resultSet.getInt("id"))
                    .description(resultSet.getString("description"))
                    .model(resultSet.getString("model"))
                    .price(resultSet.getInt("price"))
                    .title(resultSet.getString("title"))
                    .markLogoId(resultSet.getInt("mark_logo_id"))
                    .country(resultSet.getString("country"))
                    .build();
        return null;
    };

    private final JdbcTemplate jdbcTemplate;

    public CarsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(Integer id) {
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Car entity) {
        jdbcTemplate.update(
                SQL_INSERT,
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getModel(),
                findIdMark(entity.getTitle()),
                findInCountry(entity.getCountry())
        );
    }

    private Integer findIdMark(String i){
        return jdbcTemplate.query(SQL_GET_MARK_ID, new Object[]{i}, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()) {
                    return resultSet.getInt("mark_logo_id");
                }
                return null;
            }
        });
    }

    private Integer findInCountry(String i){
        return jdbcTemplate.query(SQL_GET_COUNTRY, new Object[]{i}, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()){
                    return resultSet.getInt("id");
                }
                return null;
            }
        });
    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void remove(Car entity) {

    }

    @Override
    public void removeById(Car entity) {

    }

    @Override
    public List<CarDto> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, rowMapper);
    }

    @Override
    public CarDto getById(Integer id) {
        return jdbcTemplate.query(SQL_GET_BY_ID, new Object[]{id}, resultSetExtractor);
    }

    @Override
    public Optional<List<CarDto>> getFitCars(String example) {
        return Optional.of(
                jdbcTemplate.query(
                        SQL_SEARCH+" '"+example+"%'",
                        rowMapper
                )
        );
    }
}
