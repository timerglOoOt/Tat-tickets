package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.SectionRepository;
import tat_tickets.models.Section;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class SectionRepositoryImpl implements SectionRepository {
    //language=sql
    private final static String SQL_INSERT = "insert into sections(capacity) values (?);";
    //language=sql
    private final static String SQL_UPDATE = "update sections set capacity = ? where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_ID = "select * from sections where id = ?;";
    //language=sql
    private final static String SQL_FIND_ALL = "select * from sections;";
    //language=sql
    private final static String SQL_DELETE_BY_ID = "delete from sections where id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Section> rowMapper = (row, rowNumber) -> Section.builder()
            .id((Integer) row.getObject("id"))
            .capacity(row.getInt("capacity"))
            .build();

    public SectionRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Section> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Section> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public Section save(Section item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setInt(1, item.getCapacity());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(Section item) {
        jdbcTemplate.update(SQL_UPDATE, item.getCapacity(), item.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
