package com.pfernand.pfuser.domain;

import com.pfernand.pfuser.model.Email;
import com.pfernand.pfuser.model.User;
import com.pfernand.pfuser.repository.UserDao;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.Optional;

@Named
@AllArgsConstructor
public class UserServiceJdbi implements UserService {

    private final UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.insert(user);
    }

    @Override
    public Optional<User> getUser(String uuid) {
        return Optional.ofNullable(userDao.getUserByUuid(uuid));
    }

    @Override
    public Optional<User> getUser(Email email) {
        return Optional.ofNullable(userDao.getUserByEmail(email.getEmail()));
    }
}
