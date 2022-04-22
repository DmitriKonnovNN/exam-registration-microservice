package io.dmitrikonnov.clients.notifcations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {

    String firstName;
    String lastName;
    String fullName;
    String email;
    String phoneNumber;
    String message;
}
