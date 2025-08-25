package com.oidccall.feigncallslib;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@EnableConfigurationProperties(Auth0Properties.class)
@EnableFeignClients(basePackages = {"com.oidccall.feigncallslib.feignInterfaces"})
@Import({
  com.oidccall.feigncallslib.feignCalls.AuthTokenRequest.class,
  com.oidccall.feigncallslib.feignCalls.ApiV2UsersRequestLib.class,
  com.oidccall.feigncallslib.TokenFromAuth0.class,
  com.oidccall.feigncallslib.feignCalls.exceptions.RoutingErrorDecoder.class,
  com.oidccall.feigncallslib.feignCalls.exceptions.CreateUserErrorDecoder.class,
  com.oidccall.feigncallslib.feignCalls.exceptions.GetUserErrorDecoder.class,
})
public class GetAdminTokenAutoConfiguration {

}
