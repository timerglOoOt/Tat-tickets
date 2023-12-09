package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.GameRepository;
import tat_tickets.models.Game;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {
    // SQL statements
    private final static String SQL_INSERT = "insert into games(number_of_available_seats, opposing_team_name, game_date)" +
            "values (?, ?, ?);";

    private final static String SQL_UPDATE = "update games set number_of_available_seats = ?, opposing_team_name = ?, game_date = ? where id = ?;";

    private final static String SQL_FIND_BY_ID = "select * from games where id = ?;";

    private final static String SQL_FIND_BY_OPPOSING_TEAM_NAME = "select * from games where opposing_team_name = ?;";

    private final static String SQL_FIND_ALL = "select * from games;";

    private final static String SQL_DELETE_BY_ID = "delete from games where id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Game> rowMapper = (row, rowNumber) -> Game.builder()
            .id((Integer) row.getObject("id"))
            .numberOfAvailableSeats(row.getInt("number_of_available_seats"))
            .opposingTeamName(row.getString("opposing_team_name"))
            .date((Date) row.getObject("game_date"))
            .build();

    public GameRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Game> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public Game save(Game item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setInt(1, item.getNumberOfAvailableSeats());
            statement.setString(2, item.getOpposingTeamName());
            statement.setObject(3, Timestamp.valueOf(item.getDate().toString()), Types.TIMESTAMP);
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(Game item) {
        jdbcTemplate.update(SQL_UPDATE,
                item.getNumberOfAvailableSeats(),
                item.getOpposingTeamName(),
                item.getDate(),
                item.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<Game> findByOpposingTeamName(String opposingTeamName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_OPPOSING_TEAM_NAME, rowMapper, opposingTeamName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
