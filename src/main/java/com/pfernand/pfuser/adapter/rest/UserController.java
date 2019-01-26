package com.pfernand.pfuser.adapter.rest;

import com.pfernand.pfuser.core.domain.UserService;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.port.rest.UserApi;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @ApiOperation("Create a new User")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "AUTH-TOKEN",
                    value = "AUTH-TOKEN",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Exception"),
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        log.info("POST /user with params {}", user.toString());
        return ResponseEntity.ok(userService.saveUser(user));

    }

    @ApiOperation("Retrieve a User by email")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "AUTH-TOKEN",
                    value = "AUTH-TOKEN",
                    required = true,
                    dataType = "string",
                    paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pk"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Exception"),
    })
    @RequestMapping(value = "/user/{email}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("email") final String email) {
        log.info("GET /user with params {}", email);
        return ResponseEntity.ok(userService.getUserByEmail(email));

    }
}
