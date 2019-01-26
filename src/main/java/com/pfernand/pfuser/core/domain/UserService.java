package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.adapter.repository.UserJdbiDao;
import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Named;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Named
@RequiredArgsConstructor
public class UserService {

    private final UserJdbiDao userJdbiDao;
    private final BCryptPasswordEncoder encoder;

    public User saveUser(User user) {
        user.setCreatedAt(Instant.now());
        user.setUuid(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(user.getPassword()));
        userJdbiDao.insert(user);
        return user;
    }

    public User getUser(String uuid) {
        return userJdbiDao.getUserByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    public User getUserByEmail(String email) {
        return userJdbiDao.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
