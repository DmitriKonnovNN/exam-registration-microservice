package io.dmitrikonnov;

public interface NotificationService <M, R>{
    void send(M m);
    R sendWithResponse (M m);

}
