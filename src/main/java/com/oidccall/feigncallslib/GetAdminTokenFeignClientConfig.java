package com.oidccall.feigncallslib;

import com.oidccall.feigncallslib.feignCalls.exceptions.RoutingErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAdminTokenFeignClientConfig {

  @Bean
  public ErrorDecoder errorDecoder(RoutingErrorDecoder routingErrorDecoder) {
    return routingErrorDecoder;
  }

}
