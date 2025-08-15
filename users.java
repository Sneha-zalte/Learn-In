package com.example.mocktest_app;

public class users {
    String email,username,password,status,lastmessage;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }

    public users(String email, String username, String password, String status, String lastmessage) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
        this.lastmessage = lastmessage;
    }
}
