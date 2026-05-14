package com.oidccall.feigncallslib.feignCalls;

import org.springframework.stereotype.Service;

import com.oidccall.dtos.feign.ListLogsTypeDto;
import com.oidccall.feigncallslib.SingletonAdminToken;
import com.oidccall.feigncallslib.feignInterfaces.GetLogsTypeWithFeign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiV2GetLastSuccessVerificationEmailDate {

	private final GetLogsTypeWithFeign getLogsTypeWithFeign;
	private final SingletonAdminToken singletonAdminToken;

	public ListLogsTypeDto getAll(String userId) {
		String qParams = String.format("type:\"sv\" AND user_id:\"%s\"", userId);
		return this.getLogsTypeWithFeign.getLogsType(
				"Bearer " + this.singletonAdminToken.getFullToken().getAccess_token(),
				1, 0, true, "date,type,user_id", qParams);
	}

}
