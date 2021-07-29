package edu.labIV.entity;

import edu.labIV.cfg.Config;
import edu.labIV.cfg.ConfigKey;
import edu.labIV.cfg.ConfigSection;

public class Account {

    public static final int TRIES = Config.getInstance().getLoginAttempts();

    private int id;
    private String password;
    private String email;
    private boolean active;
    private int attempts;

    public Account(){

    }

    public Account(String email, String password, boolean active, int attempts) {
        setEmail(email);
        setActive(active);
        setAttempts(attempts);
        setPassword(password);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
