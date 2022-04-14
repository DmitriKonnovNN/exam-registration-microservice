package io.dmitrikonnov.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerRegistrationRequest {


    @NotBlank
    private final String firstName;
    @NotBlank private final String lastName;
    @Email @NotBlank private final String email;
    private final LocalDateTime createdAt;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomerRegistrationRequest (@JsonProperty("firstName")String firstName,
                                             @JsonProperty("lastName") String lastName,
                                             @JsonProperty("email") String email) {
        this(firstName,
                lastName,
                email,
                LocalDateTime.now());
    }

}

