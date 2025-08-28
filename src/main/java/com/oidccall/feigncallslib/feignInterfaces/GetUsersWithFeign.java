package com.oidccall.feigncallslib.feignInterfaces;

import com.oidccall.dtos.feign.ListResponseUsersDto;
import com.oidccall.feigncallslib.GetAdminTokenFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@FeignClient(
  name = "getUsers",
  url = "${auth0.domain}",
  configuration = GetAdminTokenFeignClientConfig.class)
public interface GetUsersWithFeign {

  @GetMapping(value = "/api/v2/users", consumes = "application/json")
  ListResponseUsersDto getUsersNeverLoggedIn(
    @RequestHeader("Authorization") String bearerToken,
    @RequestParam(name = "per_page", defaultValue = "100") Integer perPage,
    @RequestParam(name = "page") int page,
    @RequestParam(name = "include_totals") boolean includeTotals,
    @RequestParam(name = "fields", defaultValue = "user_id") String userId,
    @RequestParam(name = "q") String q
  ) throws ResponseStatusException;

}
