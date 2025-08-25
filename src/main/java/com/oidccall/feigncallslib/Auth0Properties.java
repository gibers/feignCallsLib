package com.oidccall.feigncallslib;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties(prefix = "auth0")
public class Auth0Properties {

    String domain;
    String clientOriginUrl;
    Auth0ManagementApi auth0ManagementApi;

    @Value
    public static class Auth0ManagementApi {
        String clientId;
        String clientSecret;
        String audience;
    }

}
