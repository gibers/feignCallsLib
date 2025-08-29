package com.oidccall.feigncallslib.feignCalls;

import com.oidccall.dtos.feign.IParamsAuthApiV2UpdateUsers;
import com.oidccall.dtos.feign.ParamsAuthApiV2UsersDto;
import com.oidccall.dtos.feign.ParamsAuthApiV2VerifEmail;
import com.oidccall.dtos.feign.ResponseAuthApiV2UsersDto;
import com.oidccall.feigncallslib.SingletonAdminToken;
import com.oidccall.feigncallslib.feignInterfaces.GetTokenWithFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiV2UsersRequestLib {

  private final GetTokenWithFeign getTokenWithFeign;
  private final SingletonAdminToken tokenFromAuth0;

  public ResponseAuthApiV2UsersDto createUserInAuth0(ParamsAuthApiV2UsersDto paramsAuthApiV2UsersDto) {
    return getTokenWithFeign.createUserApiV2Users(
      "Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(), paramsAuthApiV2UsersDto);
  }

  public ResponseAuthApiV2UsersDto getUserApiV2Users(String userId) {
    return getTokenWithFeign.getUserApiV2Users(
      "Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(), userId);
  }

  public void deleteUserApiV2Users(String userId) {
    getTokenWithFeign.deleteUserApiV2Users(
      "Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(), userId);
  }

  public void verificationEmail(ParamsAuthApiV2VerifEmail p1) {
    getTokenWithFeign.verificationEmail("Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(), p1);
  }

  public void updateUsers(String userId, IParamsAuthApiV2UpdateUsers p1) {
    getTokenWithFeign.updateUsers("Bearer " + this.tokenFromAuth0.getFullToken().getAccess_token(), userId, p1);
  }

}
