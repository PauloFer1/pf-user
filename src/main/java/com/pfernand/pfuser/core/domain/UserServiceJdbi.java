package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.adapter.repository.UserJdbiDao;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Named;

@Slf4j
@Named
public class UserServiceJdbi implements UserService {

    private final Jdbi jdbi;

    public UserServiceJdbi(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public User saveUser(User user) {
        jdbi.useExtension(UserJdbiDao.class, dao -> saveUser(user));
        return user;
    }

    @Override
    public User getUser(String uuid) {
        return jdbi.withExtension(UserJdbiDao.class, dao -> dao.getUserByUuid(uuid));
    }

    @Override
    public User getUser(Email email) {
        return jdbi.withExtension(UserJdbiDao.class, dao -> dao.getUserByEmail(email.getEmail()));
    }
}
