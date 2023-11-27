package tat_tickets.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tat_tickets.dao.UserRepository;
import tat_tickets.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    //language=sql
    private final static String SQL_INSERT = "insert into users(first_name, last_name, email, hashed_password, avatar_id) " +
            "values (?, ?, ?, ?, ?)";
    //language=sql
    private final static String SQL_UPDATE = "update users set first_name = ?, last_name = ?, email = ?, hashed_password = ?, avatar_id = ? where id = ?";
    //language=sql
    private final static String SQL_FIND_BY_ID = "select * from users where id = ?";
    //language=sql
    private final static String SQL_FIND_BY_EMAIL = "select * from users where email = ?";
    //language=sql
    private final static String SQL_FIND_ALL = "select * from users;";
    //language=sql
    private final static String SQL_DELETE_BY_ID = "delete from users where id = ?";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> rowMapper = (row, rowNumber) -> User.builder()
            .id((Integer) row.getObject("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .hashedPassword(row.getString("hashed_password"))
            .build();
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public User save(User item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setString(4, item.getEmail());
            statement.setString(3, item.getHashedPassword());
            if(item.getAvatarId() != null) {
                statement.setLong(5, item.getAvatarId());
            } else {
                statement.setNull(5, Types.NULL);
            }
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            item.setId(keyHolder.getKey().intValue());
        }
        return item;
    }

    @Override
    public void update(User item) {
        jdbcTemplate.update(SQL_UPDATE,
                item.getFirstName(),
                item.getLastName(),
                item.getEmail(),
                item.getHashedPassword(),
                item.getAvatarId(),
                item.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
