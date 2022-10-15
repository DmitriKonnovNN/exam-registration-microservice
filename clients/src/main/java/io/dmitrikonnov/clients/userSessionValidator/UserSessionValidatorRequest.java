package io.dmitrikonnov.clients.userSessionValidator;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSessionValidatorRequest {
    private final String sessionId;

}
