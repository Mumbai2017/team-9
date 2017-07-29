package com.nishitadutta.chaakri.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Dictionary;

public class order implements Parcelable{
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
    public order(int orderno, int buyerUserId, int sellerUserId, int pickupDelivery, String address, String pickuptime, Dictionary<String, Integer> menuItem)
    {
		this.orderno = orderno;
		this.buyerUserId=buyerUserId;
        this.sellerUserId=sellerUserId;
        this.pickupDelivery=pickupDelivery;
        this.address=address; //by default custumer app
        this.pickuptime=pickuptime;//by default 0
		this.menuItem = menuItem;
    }

    public static final Parcelable.Creator<order> CREATOR
            = new Parcelable.Creator<order>() {
        public order createFromParcel(Parcel in) {
            return new order(in);
        }

        public order[] newArray(int size) {
            return new order[size];
        }
    };

    private order (Parcel in){
        orderno=in.readInt();
        buyerUserId=in.readInt();
        sellerUserId=in.readInt();
        pickupDelivery=in.readInt();
        address=in.readString();
        pickuptime=in.readString();
       // menuItem=in.readValue(Dictionary.class);
    }
    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public int getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(int buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public int getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(int sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public int getPickupDelivery() {
        return pickupDelivery;
    }

    public void setPickupDelivery(int pickupDelivery) {
        this.pickupDelivery = pickupDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(String pickuptime) {
        this.pickuptime = pickuptime;
    }

    public Dictionary<String, Integer> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Dictionary<String, Integer> menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderno);
        dest.writeInt(buyerUserId);
        dest.writeInt(sellerUserId);
        dest.writeInt(pickupDelivery);
        dest.writeString(address);
        dest.writeString(pickuptime);
        dest.writeValue(menuItem);
    }
}
