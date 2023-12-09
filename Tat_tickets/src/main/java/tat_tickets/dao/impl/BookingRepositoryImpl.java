package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.BookingRepository;
import tat_tickets.models.Booking;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {
    //language=sql
    private final static String SQL_INSERT = "insert into bookings(booking_date, user_id, ticket_id, game_name)" +
            "values (?, ?, ?, ?);";
    //language=sql
    private final static String SQL_UPDATE = "update bookings set booking_date = ?, user_id = ?, ticket_id = ? where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_ID = "select * from bookings where id = ?;";
    //language=sql
    private final static String SQL_FIND_ALL = "select * from bookings;";
    //language=sql
    private final static String SQL_DELETE_BY_ID = "delete from bookings where id = ?;";
    //language=sql
    private final static String SQL_FIND_BY_USER_ID = "select * from bookings where user_id = ?;";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Booking> rowMapper = (row, rowNumber) -> Booking.builder()
            .id((Integer) row.getObject("id"))
            .bookingDate(((Date) row.getObject("booking_date")))
            .userId(row.getInt("user_id"))
            .ticketId(row.getInt("ticket_id"))
            .gameName(row.getString("game_name"))
            .build();

    public BookingRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Booking> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public Booking save(Booking item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setObject(1, Timestamp.valueOf(item.getBookingDate().toString()), Types.TIMESTAMP);
            statement.setInt(2, item.getUserId());
            statement.setInt(3, item.getTicketId());
            statement.setString(4, item.getGameName());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(Booking item) {
        jdbcTemplate.update(SQL_UPDATE,
                item.getBookingDate(),
                item.getUserId(),
                item.getTicketId(),
                item.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Booking> findByUserId(Integer userId) {
        return jdbcTemplate.query(SQL_FIND_BY_USER_ID, rowMapper, userId);
    }
}
