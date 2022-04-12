package io.dmitrikonnov.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerRegistrationRequest {


    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private Date date;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomerRegistrationRequest (@JsonProperty("firstName")String firstName,
                                             @JsonProperty("lastName") String lastName,
                                             @JsonProperty("email") String email,
                                             @JsonProperty("password") String password) {
        this(firstName,
                lastName,
                email,
                password,
                new Date (System.currentTimeMillis()));
    }

}

