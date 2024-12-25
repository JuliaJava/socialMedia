package com.itgirls.socialMedia.repository;

import com.itgirls.socialMedia.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = """
                SELECT * FROM social_user
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
                    User user = new User(rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("user_password"));
                    return user;
                }
                 );
    }

    public User getUserById(Long id) {
        String sql = """
                SELECT * 
                FROM social_user
                WHERE user_id = ?
                """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                    User user = new User(rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("user_password"));
                    return user;
                },
                id
        );
    }
}
