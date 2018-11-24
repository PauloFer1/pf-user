package com.pfernand.pfuser.port.rest;

import com.pfernand.pfuser.core.model.User;
import org.springframework.http.ResponseEntity;

public interface UserApi {

    ResponseEntity<User> createUser(final User user) throws Exception;
}
