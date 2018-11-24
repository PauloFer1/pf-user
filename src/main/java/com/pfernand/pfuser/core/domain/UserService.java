package com.pfernand.pfuser.core.domain;

import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;


public interface UserService {

    User saveUser(User user);

    User getUser(String uuid);

    User getUser(Email email);
}
