package com.oidccall.feigncallslib.feignCalls.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoutingErrorDecoder implements ErrorDecoder {
    private final Map<String, ErrorDecoder> errorDecoders;

    public RoutingErrorDecoder(ErrorDecoderAuth0 errorDecoderAuth0,
                               ErrorDecoderMessage errorDecoderMessage) {
      this.errorDecoders = new HashMap<>();
        errorDecoders.put("GetTokenWithFeign#postOauthToken(ParamsAuthTokenDto)", errorDecoderAuth0);
        errorDecoders.put("GetTokenWithFeign#createUserApiV2Users(String,ParamsAuthApiV2UsersDto)", errorDecoderAuth0);
        errorDecoders.put("GetTokenWithFeign#getUserApiV2Users(String,String)", errorDecoderMessage);
        errorDecoders.put("GetTokenWithFeign#deleteUserApiV2Users(String,String)", errorDecoderMessage);
        errorDecoders.put("GetTokenWithFeign#updateUsers(String,String,ParamsAuthApiV2UpdateUsers)", errorDecoderMessage);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorDecoder errorDecoder = errorDecoders.getOrDefault(methodKey, new Default());
        return errorDecoder.decode(methodKey, response);
    }

}
