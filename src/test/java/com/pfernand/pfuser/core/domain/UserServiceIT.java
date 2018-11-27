package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.H2Config;
import com.pfernand.pfuser.PfUserApplication;
import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.utils.SqlUtils;
import org.jdbi.v3.core.Jdbi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PfUserApplication.class, H2Config.class}
        , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIT {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);

    private static final String CREATE_USER_TABLE = "CREATE TABLE PF_USER  (\n" +
            "\tUUID VARCHAR(36)  NOT NULL PRIMARY KEY,\n" +
            "\tCREATED_AT DATETIME NOT NULL,\n" +
            "\tFIRST_NAME VARCHAR(250) ,\n" +
            "\tLAST_NAME VARCHAR(250) ,\n" +
            "\tEMAIL VARCHAR(250) ,\n" +
            "\tPASSWORD VARCHAR(250)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static final User USER = User.builder()
            .firstName("Paulo")
            .lastName("Fernandes")
            .password("password")
            .email("test@mail.com")
            .uuid("uuid")
            .createdAt(DATETIME_FORMATTER.parse("2018-11-27 23:13:14", Instant::from))
            .build();

    @Autowired
    private UserService userService;

    @Autowired
    Jdbi jdbi;

    @Before
    public void setUp() throws Exception {
        SqlUtils.runSqlScript(jdbi, CREATE_USER_TABLE);
    }

    @Test
    public void saveUserTest() {
        // Given
        // When
        userService.saveUser(USER);
        User user = userService.getUser(USER.getUuid());

        assertEquals(USER, user);
    }

    @Test
    public void getUserByEmailTest() {
        // Given
        // When
        userService.saveUser(USER);
        User user = userService.getUser(new Email(USER.getEmail()));

        assertEquals(USER, user);
    }

}
