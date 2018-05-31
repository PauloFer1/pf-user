package com.pfernand.pfuser.domain;

import com.pfernand.pfuser.model.Email;
import com.pfernand.pfuser.model.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> getUser(String uuid);

    Optional<User> getUser(Email email);
}
