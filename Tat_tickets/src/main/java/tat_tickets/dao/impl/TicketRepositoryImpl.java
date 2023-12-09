package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.TicketRepository;
import tat_tickets.models.Ticket;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository {
    // SQL statements
    private final static String SQL_INSERT = "insert into tickets(section_id, seat_id, price, status, game_id) values (?, ?, ?, ?, ?);";
    private final static String SQL_UPDATE = "update tickets set section_id = ?, seat_id = ?, price = ?, status = ?, game_id = ? where id = ?;";
    private final static String SQL_FIND_BY_ID = "select * from tickets where id = ?;";
    private final static String SQL_FIND_BY_GAME_ID = "select * from tickets where game_id = ?;";
    private final static String SQL_FIND_BY_STATUS = "select * from tickets where status = ?;";
    private final static String SQL_FIND_ALL = "select * from tickets;";
    private final static String SQL_DELETE_BY_ID = "delete from tickets where id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Ticket> rowMapper = (row, rowNumber) -> Ticket.builder()
            .id((Integer) row.getObject("id"))
            .sectionId(row.getInt("section_id"))
            .seatId(row.getInt("seat_id"))
            .price(row.getInt("price"))
            .status(row.getBoolean("status"))
            .gameId(row.getInt("game_id"))
            .build();

    public TicketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Ticket> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Ticket> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }


    @Override
    public Ticket save(Ticket item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setInt(1, item.getSectionId());
            statement.setInt(2, item.getSeatId());
            statement.setInt(3, item.getPrice());
            statement.setBoolean(4, item.isStatus());
            statement.setInt(5, item.getGameId());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(Ticket item) {
        jdbcTemplate.update(SQL_UPDATE,
                item.getSectionId(),
                item.getSeatId(),
                item.getPrice(),
                item.isStatus(),
                item.getGameId(),
                item.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Ticket> findByGameId(Integer gameId) {
        return jdbcTemplate.query(SQL_FIND_BY_GAME_ID, rowMapper, gameId);
    }

    @Override
    public List<Ticket> findByStatus(boolean status) {
        return jdbcTemplate.query(SQL_FIND_BY_STATUS, rowMapper, status);
    }
}
