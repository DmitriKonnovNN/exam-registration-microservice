package io.dmitrikonnov.clients.notifcations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEmail {

    String firstName;
    String lastName;
    String email;
    String message;
}
