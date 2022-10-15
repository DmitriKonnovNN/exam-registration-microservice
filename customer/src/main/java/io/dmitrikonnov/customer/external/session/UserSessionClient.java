package io.dmitrikonnov.customer.external.session;

import feign.QueryMap;
import feign.RequestLine;
import io.dmitrikonnov.clients.userSessionValidator.UserSessionValidatorRequest;
import io.dmitrikonnov.clients.userSessionValidator.UserSessionValidatorResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface UserSessionClient {
    @RequestLine("GET /user-sessions/validate")
    UserSessionValidatorResponse validate(@QueryMap Map<String, Object> queryMap);

    @RequestLine("GET /user-sessions/validate")
    default UserSessionValidatorResponse validate(UUID sessionId){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("sessionId", sessionId.toString());
        return validate(queryMap);
    }

    @RequestLine("GET /user-sessions/validateRequest")
    UserSessionValidatorResponse validateSessionRequest(@QueryMap UserSessionValidatorRequest request);

    @RequestLine("GET /user-sessions/validateRequest")
    default UserSessionValidatorResponse validateSessionRequest(UUID sessionId){

        return validateSessionRequest(new UserSessionValidatorRequest(sessionId.toString()));
    }

}
