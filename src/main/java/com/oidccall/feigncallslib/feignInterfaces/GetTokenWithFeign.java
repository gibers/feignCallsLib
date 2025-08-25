package com.oidccall.feigncallslib.feignInterfaces;

import com.oidccall.dtos.feign.IParamsAuthApiV2UpdateUsers;
import com.oidccall.dtos.feign.ParamsAuthApiV2UsersDto;
import com.oidccall.dtos.feign.ParamsAuthApiV2VerifEmail;
import com.oidccall.dtos.feign.ParamsAuthTokenDto;
import com.oidccall.dtos.feign.ResponseAuthApiV2UsersDto;
import com.oidccall.dtos.feign.ResponseAuthApiV2VerifEmail;
import com.oidccall.dtos.feign.ResponseAuthTokenDto;
import com.oidccall.feigncallslib.GetAdminTokenFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

@FeignClient(
  name = "userClient",
  url = "${auth0.domain}",
  configuration = GetAdminTokenFeignClientConfig.class)
public interface GetTokenWithFeign {

  @PostMapping("/oauth/token")
  ResponseAuthTokenDto postOauthToken(@RequestBody ParamsAuthTokenDto paramsAuthTokenDto);

  @PostMapping("/api/v2/users")
  ResponseAuthApiV2UsersDto createUserApiV2Users(
    @RequestHeader("Authorization") String bearerToken,
    @RequestBody ParamsAuthApiV2UsersDto paramsAuthApiV2UsersDto);

  @GetMapping(value = "/api/v2/users/{userId}", consumes = "application/json")
  ResponseAuthApiV2UsersDto getUserApiV2Users(
    @RequestHeader("Authorization") String bearerToken,
    @PathVariable("userId") String userId
  ) throws ResponseStatusException;

  @DeleteMapping(value = "/api/v2/users/{userId}", consumes = "application/json")
  ResponseAuthApiV2UsersDto deleteUserApiV2Users(
    @RequestHeader("Authorization") String bearerToken,
    @PathVariable("userId") String userId
  ) throws ResponseStatusException;

  @PostMapping("/api/v2/jobs/verification-email")
  ResponseAuthApiV2VerifEmail verificationEmail(
    @RequestHeader("Authorization") String bearerToken,
    @RequestBody ParamsAuthApiV2VerifEmail p1);

  @PatchMapping(value="/api/v2/users/{userId}", consumes = "application/json", produces = "application/json")
  ResponseAuthApiV2UsersDto updateUsers(@RequestHeader("Authorization") String bearerToken,
                                        @PathVariable("userId") String userId,
                                        @RequestBody IParamsAuthApiV2UpdateUsers p1);

}
