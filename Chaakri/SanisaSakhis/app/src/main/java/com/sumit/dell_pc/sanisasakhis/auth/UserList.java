package com.sumit.dell_pc.sanisasakhis.auth;

/**
 * Created by DELL_PC on 7/27/2017.
 */

public class UserList {
    private String userId;
    private String userName;
    private String userEmail;

    public UserList(String userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
