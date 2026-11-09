package com.example;

import javafx.event.Event;
import javafx.event.EventType;

/** Activity 4 - a custom event carrying the username that logged in. */
public class LoginSuccessEvent extends Event {

    // The event type: a unique name for this kind of event
    public static final EventType<LoginSuccessEvent> LOGIN_SUCCESS =
            new EventType<>(Event.ANY, "LOGIN_SUCCESS");

    private final String username;

    public LoginSuccessEvent(String username) {
        super(LOGIN_SUCCESS);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
