package com.sumit.dell_pc.sanisasakhis.models;


import java.util.Dictionary;

public class order {
    public int orderno;
    public int buyerUserId;
    public int sellerUserId;
    public int pickupDelivery;
    public String address;
    public String pickuptime;
	public Dictionary<String, Integer> menuItem;
    public order(String cusAddress)
    {
		this.address=cusAddress;
	}
    public order(int orderno,int buyerUserId,int sellerUserId,int pickupDelivery,String address,String pickuptime, Dictionary<String, Integer> menuItem)
    {
		this.orderno = orderno;
		this.buyerUserId=buyerUserId;
        this.sellerUserId=sellerUserId;
        this.pickupDelivery=pickupDelivery;
        this.address=address; //by default custumer app
        this.pickuptime=pickuptime;//by default 0
		this.menuItem = menuItem;
    }
}
