package com.pfernand.pfuser.port.repository;

import com.pfernand.pfuser.core.model.User;

public interface UserRepository {

    void insert(final User user);

    User getUserByEmail(final String email);

    User getUserByUuid(final String uuid);
}
