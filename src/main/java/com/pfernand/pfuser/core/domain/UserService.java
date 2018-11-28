package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.adapter.repository.UserJdbiDao;
import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Named
public class UserService {

    private final UserJdbiDao userJdbiDao;

    public UserService(final UserJdbiDao userJdbiDao) {
        this.userJdbiDao = userJdbiDao;
    }

    public User saveUser(User user) {
        user.setCreatedAt(Instant.now());
        user.setUuid(UUID.randomUUID().toString());
        userJdbiDao.insert(user);
        return user;
    }

    public User getUser(String uuid) {
        return userJdbiDao.getUserByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    public User getUser(Email email) {
        return userJdbiDao.getUserByEmail(email.getEmail())
                .orElseThrow(() -> new UserNotFoundException(email.getEmail()));
    }
}
