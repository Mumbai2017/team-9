package com.nishitadutta.chaakri.models;

import java.util.Dictionary;

public class sakhi {
    public String username;
    public int userId;
    public String email;
    public String fname;
    public String lname;
    public int mobno;
    public int NgoOrderNo;
    public int CusOrderNo;
    public String address;
	public String pickuptime;
	public Dictionary<String, Integer> menuItem;
    public sakhi()
    {}
    public sakhi(String username, int userId, String email, String fname, String lname, int mobno, int NgoOrderNo, int CusOrderNo, String address, String pickuptime, Dictionary<String, Integer> menuItem)
    {
		this.username = username;
		this.userId=userId;
        this.email=email;
        this.fname=fname;
        this.lname=lname;
        this.mobno=mobno;
        this.NgoOrderNo=NgoOrderNo;
        this.CusOrderNo=CusOrderNo;
        this.address=address;
		this.pickuptime=pickuptime;
		this.menuItem = menuItem;
	 }

}
