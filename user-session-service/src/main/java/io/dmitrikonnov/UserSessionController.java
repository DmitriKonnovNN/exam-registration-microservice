package io.dmitrikonnov;


import io.dmitrikonnov.clients.userSessionValidator.UserSessionValidatorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserSessionController {
    @GetMapping("/user-sessions/validate")
    public UserSessionValidatorResponse validate(@RequestParam("sessionId") UUID sessionId){
        boolean isValid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000").equals(sessionId);
        return UserSessionValidatorResponse.builder().
                sessionId(sessionId).
                valid(isValid).
                build();
    }

    @GetMapping("/user-sessions/validateRequest")
    public UserSessionValidatorResponse validateRequest(@RequestParam("sessionId") UUID sessionId){
        boolean isValid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000").equals(sessionId);
        return UserSessionValidatorResponse.builder().
                sessionId(sessionId).
                valid(isValid).
                build();
    }
}
