package com.example.muhammadsalsabiluas2021;

public class AuthorizationResponse {

    private LoggedInUser loggedInUser;
    private String token;

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public String getToken() {
        return token;
    }
}
