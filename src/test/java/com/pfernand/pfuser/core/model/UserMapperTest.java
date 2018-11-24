package com.pfernand.pfuser.core.model;

import org.jdbi.v3.core.statement.StatementContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTest {

    @Mock
    private ResultSet resultSet;

    @Mock
    private StatementContext statementContext;

    private UserMapper userMapper = new UserMapper();

    private static final String FIRST_NAME = "Paulo";
    private static final String LAST_NAME = "Fernandes";
    private static final String EMAIL = "paulo@mail.com";
    private static final String CREATED_AT = "2018-01-01 10:10:10";
    private static final String PASSWORD = "*BGTFER675GTF890H";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void mapFillAllFields() throws SQLException {
        // Given
        // When
        Mockito.when(resultSet.getString("FIRST_NAME")).thenReturn(FIRST_NAME);
        Mockito.when(resultSet.getString("LAST_NAME")).thenReturn(LAST_NAME);
        Mockito.when(resultSet.getString("EMAIL")).thenReturn(EMAIL);
        Mockito.when(resultSet.getString("CREATED_AT")).thenReturn(CREATED_AT);
        Mockito.when(resultSet.getString("PASSWORD")).thenReturn(PASSWORD);
        User user = userMapper.map(resultSet, statementContext);

        // Then
        assertEquals(user.getFirstName(), FIRST_NAME);
        assertEquals(user.getLastName(), LAST_NAME);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getCreatedAt(), LocalDateTime.parse(CREATED_AT, FORMATTER));
        assertEquals(user.getPassword(), PASSWORD);
    }

}