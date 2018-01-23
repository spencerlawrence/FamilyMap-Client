package com.example.spencer.familymap.DTOs;

public class LoginResponse {
    private String token;
    private String userName;
    private String personID;
    private String message;

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    public String getPersonID() {
        return personID;
    }

    public String getMessage() {
        return message;
    }
}
