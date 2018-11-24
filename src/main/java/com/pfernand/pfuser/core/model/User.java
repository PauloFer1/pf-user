package com.pfernand.pfuser.core.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class User {
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
