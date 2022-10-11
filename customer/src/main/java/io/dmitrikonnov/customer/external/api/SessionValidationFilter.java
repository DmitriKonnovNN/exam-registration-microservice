package io.dmitrikonnov.customer.external.api;
import io.dmitrikonnov.customer.external.session.UserSessionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class SessionValidationFilter implements Filter {
    private final UserSessionClient client;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        var sessionIdHeader = httpServletRequest.getHeader("X-Session-id");
        if (sessionIdHeader == null) {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());
        }else {
            UUID sessionIdUUID = UUID.fromString(sessionIdHeader);
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("sessionId", sessionIdUUID);
            queryMap.put("anotherId", UUID.randomUUID().toString());
            var resp = client.validate(queryMap);
            if(!resp.isValid()){
                httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());
            }else
            {
                chain.doFilter(request,response);
            }}


    }
}
