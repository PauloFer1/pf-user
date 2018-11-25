package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.port.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@Named
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        userRepository.insert(user);
        return user;
    }

    public User getUser(String uuid) {
        return userRepository.getUserByUuid(uuid);
    }

    public User getUser(Email email) {
        return userRepository.getUserByEmail(email.getEmail());
    }
}
