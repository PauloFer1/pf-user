package com.pfernand.pfuser.core.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Data
@Builder
public class User {
    private final String uuid;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Instant createdAt;
}
