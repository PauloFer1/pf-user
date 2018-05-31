package com.pfernand.pfuser.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
}
