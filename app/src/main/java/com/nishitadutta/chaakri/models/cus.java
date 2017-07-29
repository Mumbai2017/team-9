package com.nishitadutta.chaakri.models;


public class cus {
    public String username;
    public int userId;
    public String email;
    public String fname;
    public String lname;
    public int mobno;
    public int orderno;
    public String address;
    public cus()
    {}
    public cus(String username, int userId, String email, String fname, String lname, int mobno, int orderno, String address)
    {
		this.username = username;
		this.userId=userId;
        this.email=email;
        this.fname=fname;
        this.lname=lname;
        this.mobno=mobno;
        this.orderno=orderno;
        this.address=address;
    }

}
