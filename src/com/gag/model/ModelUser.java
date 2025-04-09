package com.gag.model;

public class ModelUser {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private int userID;
    private String userName;
    private String email;
    private String password;
    private String userType;

    public ModelUser(int userID, String userName, String email, String password, String userType) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public ModelUser() {
        this.userType = USER; // Par défaut, un nouvel utilisateur est un utilisateur normal
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isAdmin() {
        return ADMIN.equals(userType);
    }
}
