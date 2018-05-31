package com.pfernand.pfuser.repository;

import com.pfernand.pfuser.model.User;
import com.pfernand.pfuser.model.UserMapper;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;


public interface UserDao extends SqlObject {

    String INSERT =
            "insert into "
                    + "PF_USER(CREATED_AT, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD)"
                    + "VALUES(:createdAt, :firstName, :lastName, :email, :password)";

    String SELECT_USER_BY_EMAIL =
            "select " +
                    "CREATED_AT, " +
                    "FIRST_NAME, " +
                    "LAST_NAME, " +
                    "EMAIL, " +
                    "PASSWORD " +
                    "from PF_USER " +
                    "where " +
                    "EMAIL = :email";

    String SELECT_USER_BY_UUID =
            "select " +
                    "CREATED_AT, " +
                    "FIRST_NAME, " +
                    "LAST_NAME, " +
                    "EMAIL, " +
                    "PASSWORD " +
                    "from PF_USER " +
                    "where " +
                    "UUID = :uuid";

    @SqlUpdate(INSERT)
    void insert(@BindBean User user);

    @SqlQuery(SELECT_USER_BY_EMAIL)
    @RegisterRowMapper(UserMapper.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlQuery(SELECT_USER_BY_UUID)
    @RegisterRowMapper(UserMapper.class)
    User getUserByUuid(@Bind("uuid") String uuid);
}
