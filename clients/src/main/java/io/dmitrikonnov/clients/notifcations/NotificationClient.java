package io.dmitrikonnov.clients.notifcations;


import lombok.AllArgsConstructor;



@AllArgsConstructor
public class NotificationClient <T> {
   NotificationClientResolver resolver;

    public T sendEmail(NotificationRequest notificationRequest){
        return (T)resolver.sendEmail(notificationRequest);
    }
}

