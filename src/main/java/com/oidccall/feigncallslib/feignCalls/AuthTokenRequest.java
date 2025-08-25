package com.oidccall.feigncallslib.feignCalls;

import com.oidccall.dtos.feign.ParamsAuthTokenDto;
import com.oidccall.dtos.feign.ResponseAuthTokenDto;
import com.oidccall.feigncallslib.Auth0Properties;
import com.oidccall.feigncallslib.feignInterfaces.GetTokenWithFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthTokenRequest {

  private final GetTokenWithFeign getTokenWithFeign;
  private final Auth0Properties auth0Properties;

  public AuthTokenRequest(GetTokenWithFeign getTokenWithFeign, Auth0Properties auth0Properties) {
    this.getTokenWithFeign = getTokenWithFeign;
    this.auth0Properties = auth0Properties;
  }

  public ResponseAuthTokenDto requestToken() {
    ParamsAuthTokenDto paramsAuthTokenDto = new ParamsAuthTokenDto(
      auth0Properties.getAuth0ManagementApi().getClientId(),
      auth0Properties.getAuth0ManagementApi().getClientSecret(),
      auth0Properties.getAuth0ManagementApi().getAudience(),
      "client_credentials");
    return getTokenWithFeign.postOauthToken(paramsAuthTokenDto);
  }

}
