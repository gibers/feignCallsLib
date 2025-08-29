package com.oidccall.feigncallslib;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oidccall.dtos.feign.ParamsAuthTokenDto;
import com.oidccall.dtos.feign.ResponseAuthTokenDto;
import com.oidccall.feigncallslib.feignInterfaces.GetTokenWithFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class SingletonFunctionalUserToken {

  private final GetTokenWithFeign getTokenWithFeign;
  private volatile ResponseAuthTokenDto token;
  private final Auth0Properties auth0Properties;

  public SingletonFunctionalUserToken(GetTokenWithFeign getTokenWithFeign, Auth0Properties auth0Properties) {
    this.getTokenWithFeign = getTokenWithFeign;
    this.auth0Properties = auth0Properties;
  }

  public synchronized ResponseAuthTokenDto getFullToken() {
    if (this.token == null || this.isTokenExpired()) {
      this.token = this.requestToken();
    }
    return token;
  }

  private ResponseAuthTokenDto requestToken() {
    ParamsAuthTokenDto paramsAuthTokenDto = new ParamsAuthTokenDto(
      auth0Properties.getFunctionalUser().getClientId(),
      auth0Properties.getFunctionalUser().getClientSecret(),
      auth0Properties.getFunctionalUser().getAudience(),
      "client_credentials");
    return this.getTokenWithFeign.postOauthToken(paramsAuthTokenDto);
  }

  private boolean isTokenExpired() {
    String accessToken = this.token.getAccess_token();
    DecodedJWT jwt = JWT.decode(accessToken);
    Date expiryDate = jwt.getExpiresAt();
    long _10minutes = 1000 * 60 * 10;
    long rajout = new Date().getTime() + _10minutes;
    return (expiryDate == null) || expiryDate.before(new Date(rajout));
  }
}
