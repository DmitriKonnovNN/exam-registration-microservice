package io.dmitrikonnov.customer;

public interface Notification {
    void notifyViaEmail (String firstName, String lastName, String email,String message);
}
