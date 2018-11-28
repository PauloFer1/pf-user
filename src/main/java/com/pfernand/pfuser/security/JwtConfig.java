package com.pfernand.pfuser.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;

@Data
@Named
public class JwtConfig {

    private final String uri;

    private final String header;

    private final String prefix;

    private final int expiration;

    private final String secret;

    @Inject
    public JwtConfig(@Value("${security.jwt.uri:/auth/**}") final String uri,
                     @Value("${security.jwt.header:Authorization}") final String header,
                     @Value("${security.jwt.prefix:Bearer }") final String prefix,
                     @Value("${security.jwt.expiration:#{24*60*60}}") final int expiration,
                     @Value("${security.jwt.secret:JwtSecretKey}") final String secret) {
        this.uri = uri;
        this.header = header;
        this.prefix = prefix;
        this.expiration = expiration;
        this.secret = secret;
    }
}
