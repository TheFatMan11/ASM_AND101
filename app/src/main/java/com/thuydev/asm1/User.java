package com.thuydev.asm1;

public class User {
    private String User;
    private String Pass;
    private boolean save = false;

    public User(String user, String pass) {
        User = user;
        Pass = pass;
    }

    public User(String user, String pass, boolean save) {
        User = user;
        Pass = pass;
        this.save = save;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }
}
