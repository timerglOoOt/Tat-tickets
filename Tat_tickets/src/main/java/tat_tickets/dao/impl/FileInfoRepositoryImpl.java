package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.FileInfoRepository;
import tat_tickets.models.FileInfo;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class FileInfoRepositoryImpl implements FileInfoRepository {
    //language=sql
    private final static String SQL_INSERT = "insert into file_info(original_file_name, storage_file_name, size, type)" +
            "values (?, ?, ?, ?);";
    //language=sql
    private final static String SQL_UPDATE = "update file_info set original_file_name = ?, storage_file_name = ?, size = ?, type = ? where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_ID = "select * from file_info where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_ORIGINAL_FILE_NAME = "select * from file_info where original_file_name = ?;";
    //language=sql
    private final static String SQL_FIND_ALL = "select * from file_info;";
    //language=sql
    private final static String SQL_DELETE_BY_ID = "delete from file_info where id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<FileInfo> rowMapper = (row, rowNumber) -> FileInfo.builder()
            .id((Integer) row.getObject("id"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .size(row.getLong("size"))
            .type(row.getString("type"))
            .build();

    public FileInfoRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<FileInfo> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public FileInfo save(FileInfo item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setString(1, item.getOriginalFileName());
            statement.setString(2, item.getStorageFileName());
            statement.setLong(3, item.getSize());
            statement.setString(4, item.getType());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(FileInfo item) {
        jdbcTemplate.update(SQL_UPDATE,
                item.getOriginalFileName(),
                item.getStorageFileName(),
                item.getSize(),
                item.getType(),
                item.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<FileInfo> findByOriginalFileName(String originalFileName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ORIGINAL_FILE_NAME, rowMapper, originalFileName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
