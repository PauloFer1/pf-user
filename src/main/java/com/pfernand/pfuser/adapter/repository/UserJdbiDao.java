package com.pfernand.pfuser.adapter.repository;

import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.core.model.UserMapper;
import com.pfernand.pfuser.port.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Named;
import java.util.Optional;

@Named
@RequiredArgsConstructor
public class UserJdbiDao implements UserDao {

    private static final String INSERT =
            "insert into "
                    + "PF_USER(UUID, CREATED_AT, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) "
                    + "VALUES(:uuid, :createdAt, :firstName, :lastName, :email, :password)";

    private static final String SELECT_USER_BY_EMAIL =
            "select " +
                    "UUID, " +
                    "CREATED_AT, " +
                    "FIRST_NAME, " +
                    "LAST_NAME, " +
                    "EMAIL, " +
                    "PASSWORD " +
                    "from PF_USER " +
                    "where " +
                    "EMAIL = :email";

    private static final String SELECT_USER_BY_UUID =
            "select " +
                    "UUID, " +
                    "CREATED_AT, " +
                    "FIRST_NAME, " +
                    "LAST_NAME, " +
                    "EMAIL, " +
                    "PASSWORD " +
                    "FROM PF_USER " +
                    "WHERE " +
                    "UUID = :uuid";

    private final Jdbi jdbi;
    private final UserMapper userMapper;


    public void insert(final User user) {
        jdbi.withHandle(handle -> handle.createUpdate(INSERT)
                .bindBean(user)
                .execute()
        );
    }

    public Optional<User> getUserByEmail(final String email) {
        return jdbi.withHandle(handle -> handle.createQuery(SELECT_USER_BY_EMAIL)
                .bind("email", email)
                .map(userMapper)
                .findFirst()
        );
    }

    public Optional<User> getUserByUuid(final String uuid) {
        return jdbi.withHandle(handle -> handle.createQuery(SELECT_USER_BY_UUID)
                .bind("uuid", uuid)
                .map(userMapper)
                .findFirst()
        );
    }
}

