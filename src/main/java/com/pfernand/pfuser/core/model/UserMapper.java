package com.pfernand.pfuser.core.model;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Named
public class UserMapper implements RowMapper<User> {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);

    @Override
    public User map(ResultSet rs, StatementContext ctx) throws SQLException {

        return User.builder()
                .uuid(rs.getString("UUID"))
                .firstName(rs.getString("FIRST_NAME"))
                .lastName(rs.getString("LAST_NAME"))
                .email(rs.getString("EMAIL"))
                .createdAt(convertToInstant(rs.getString("CREATED_AT")))
                .password(rs.getString("PASSWORD"))
                .build();
    }

    private Instant convertToInstant(String date) {
        return DATETIME_FORMATTER.parse(date.substring(0, 19), Instant::from);
    }
}
