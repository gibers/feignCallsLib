package com.oidccall.feigncallslib.feignInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.oidccall.dtos.feign.ListLogsTypeDto;
import com.oidccall.feigncallslib.GetAdminTokenFeignClientConfig;

@FeignClient(
  name = "getLogsType",
  url = "${auth0.domain}",
  configuration = GetAdminTokenFeignClientConfig.class)
public interface GetLogsTypeWithFeign {

  @GetMapping(value = "/api/v2/logs", consumes = "application/json")
  ListLogsTypeDto getLogsType(
    @RequestHeader("Authorization") String bearerToken,
    @RequestParam(name = "per_page", defaultValue = "100") Integer perPage,
    @RequestParam(name = "page") int page,
    @RequestParam(name = "include_totals") boolean includeTotals,
    @RequestParam(name = "fields", defaultValue = "date") String date,
    @RequestParam(name = "search") String search
  ) throws ResponseStatusException;

}
