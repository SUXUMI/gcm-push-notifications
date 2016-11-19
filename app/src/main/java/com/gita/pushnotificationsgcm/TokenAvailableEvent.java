package com.gita.pushnotificationsgcm;

/**
 * Created by alex on 11/19/2016
 */

public class TokenAvailableEvent {
    private String token;

    public TokenAvailableEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
