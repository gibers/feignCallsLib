package com.oidccall.feigncallslib;

import com.oidccall.feigncallslib.feignCalls.ApiV2GetUsersRequest;
import com.oidccall.feigncallslib.feignCalls.ApiV2UsersRequestLib;
import com.oidccall.feigncallslib.feignCalls.AuthTokenRequest;
import com.oidccall.feigncallslib.feignCalls.exceptions.ErrorDecoderAuth0;
import com.oidccall.feigncallslib.feignCalls.exceptions.ErrorDecoderMessage;
import com.oidccall.feigncallslib.feignCalls.exceptions.RoutingErrorDecoder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@EnableConfigurationProperties({Auth0Properties.class})
@EnableFeignClients(basePackages = {"com.oidccall.feigncallslib.feignInterfaces"})
@Import({
  AuthTokenRequest.class,
  ApiV2UsersRequestLib.class,
  ApiV2GetUsersRequest.class,
  SingletonAdminToken.class,
  SingletonFunctionalUserToken.class,
  RoutingErrorDecoder.class,
  ErrorDecoderAuth0.class,
  ErrorDecoderMessage.class,
})
public class GetAdminTokenAutoConfiguration {

}
