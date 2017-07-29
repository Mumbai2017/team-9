package com.sumit.dell_pc.sanishasakhis.auth;

/**
 * Created by DELL_PC on 7/27/2017.
 */

public class UserList {
    private String userId;
    private String userName;
    private String userEmail;
    private String mobileNo;
    private String ngoOrder;
    private String consumerOrder;
    private String InvetoryFlover;
    private String InventoryNo;

    public UserList(String userId, String userName, String userEmail, String mobileNo, String ngoOrder, String consumerOrder, String invetoryFlover, String inventoryNo) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.mobileNo = mobileNo;
        this.ngoOrder = ngoOrder;
        this.consumerOrder = consumerOrder;
        InvetoryFlover = invetoryFlover;
        InventoryNo = inventoryNo;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public String getNgoOrder() {
        return ngoOrder;
    }

    public String getConsumerOrder() {
        return consumerOrder;
    }

    public String getInvetoryFlover() {
        return InvetoryFlover;
    }

    public String getInventoryNo() {
        return InventoryNo;
    }
}
