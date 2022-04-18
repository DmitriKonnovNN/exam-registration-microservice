package io.dmitrikonnov.clients.notifcations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEmail {

    String firstName;
    String lastName;
    String email;
    String message;
}
