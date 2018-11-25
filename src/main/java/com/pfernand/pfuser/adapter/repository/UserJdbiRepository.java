package com.pfernand.pfuser.adapter.repository;

import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.port.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Named;

@Slf4j
@Named
public class UserJdbiRepository implements UserRepository {

    private final Jdbi jdbi;

    public UserJdbiRepository(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void insert(final User user) {
        log.debug("Inserting User: {}", user);
        jdbi.useExtension(UserJdbiDao.class, dao -> insert(user));
    }

    public User getUserByEmail(final String email) {
        log.debug("Retrieving User with email: {}", email);
        return jdbi.withExtension(UserJdbiDao.class, dao -> dao.getUserByEmail(email));
    }

    public User getUserByUuid(final String uuid) {
        log.debug("Retrieving User with UUID: {}", uuid);
        return jdbi.withExtension(UserJdbiDao.class, dao -> dao.getUserByUuid(uuid));
    }
}
