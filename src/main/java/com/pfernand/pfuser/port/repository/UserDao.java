package com.pfernand.pfuser.port.repository;

import com.pfernand.pfuser.core.model.User;

import java.util.Optional;

public interface UserDao {
    void insert(final User user);

    Optional<User> getUserByEmail(final String email);

    Optional<User> getUserByUuid(final String uuid);
}
