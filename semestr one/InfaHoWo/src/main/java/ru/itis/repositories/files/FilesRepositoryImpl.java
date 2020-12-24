package ru.itis.repositories.files;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class FilesRepositoryImpl implements FilesRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into photos_cars(storage_photo_car, original_photo_car, type) " +
            "values (?, ?, ?)";

    //language=SQl
    private final static String SQL_SELECT_BY_ID = "select * from photos_cars where id = ?";

    private JdbcTemplate jdbcTemplate;

    public FilesRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> filesRowMapper = (row, rowNumber) ->
        FileInfo.builder()
                .id(row.getInt("id"))
                .originalFileName(row.getString("original_photo_car"))
                .storageFileName(row.getString("storage_photo_car"))
                .type(row.getString("type"))
                .build();


    @Override
    public List<FileInfo> findAll() {
        return null;
    }

    @Override
    public FileInfo findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, filesRowMapper,id);
    }

    @Override
    public Optional<FileInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(FileInfo entity) {
        jdbcTemplate.update(
                SQL_INSERT,
                entity.getStorageFileName(),
                entity.getOriginalFileName(),
                entity.getType()
        );

    }

    @Override
    public void update(FileInfo entity) {

    }

    @Override
    public void remove(FileInfo entity) {

    }

    @Override
    public void removeById(FileInfo entity) {

    }
}
