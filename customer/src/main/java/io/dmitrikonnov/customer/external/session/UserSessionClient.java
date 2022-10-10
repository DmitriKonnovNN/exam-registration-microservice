package io.dmitrikonnov.customer.external.session;

import feign.QueryMap;
import feign.RequestLine;
import io.dmitrikonnov.clients.userSessionValidator.UserSessionValidatorResponse;

import java.util.Map;

public interface UserSessionClient {
    @RequestLine("GET /user-sessions/validate")
    UserSessionValidatorResponse validate(@QueryMap Map<String, Object> queryMap);
}
