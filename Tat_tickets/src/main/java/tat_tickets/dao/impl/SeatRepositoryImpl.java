package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.SeatRepository;
import tat_tickets.models.Seat;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class SeatRepositoryImpl implements SeatRepository {
    //language=sql
    private final static String SQL_INSERT = "insert into seats(section_id, name) values (?, ?);";
    //language=sql
    private final static String SQL_UPDATE = "update seats set section_id = ?, name = ? where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_ID = "select * from seats where id = ?;";
    //language=sql
    private final static String SQL_FIND_ALL = "select * from seats;";
    //language=sql
    private final static String SQL_FIND_ALL_BY_SECTION_ID = "select * from seats where section_id = ?;";
    //language=sql
    private final static String SQL_DELETE_BY_ID = "delete from seats where id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Seat> rowMapper = (row, rowNumber) -> Seat.builder()
            .id((Integer) row.getObject("id"))
            .sectionId(row.getInt("section_id"))
            .name(row.getString("name"))
            .build();

    public SeatRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Seat> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Seat> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public List<Seat> findAllBySectionId(Integer id) {
        return jdbcTemplate.query(SQL_FIND_ALL_BY_SECTION_ID, rowMapper, id);
    }

    @Override
    public Seat save(Seat item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setInt(1, item.getSectionId());
            statement.setString(2, item.getName());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(Seat item) {
        jdbcTemplate.update(SQL_UPDATE, item.getSectionId(), item.getName(), item.getId());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
