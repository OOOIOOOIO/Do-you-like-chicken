package com.sh.chicken.global.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtInfoProperties {
    private String secret;
    private Long accessTokenExpireMin;
    private Long refreshTokenExpireMin;


}
