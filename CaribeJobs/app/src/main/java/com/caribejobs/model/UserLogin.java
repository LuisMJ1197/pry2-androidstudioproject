package com.caribejobs.model;

public class UserLogin extends User {
    private String username;
    private String password;
    private boolean logged = false;

    public UserLogin(String username, String password) {
        super(username);
        this.username = username;
        this.password = password;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
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

}
