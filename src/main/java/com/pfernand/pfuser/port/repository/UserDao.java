package com.pfernand.pfuser.port.repository;

import com.pfernand.pfuser.core.model.User;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

public interface UserDao {
    void insert(@BindBean User user);

    User getUserByEmail(@Bind("email") String email);

    User getUserByUuid(@Bind("uuid") String uuid);
}
