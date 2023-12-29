package com.OrdersandNotificationsManagement.demo.Model;

import java.util.ArrayList;

public class Customer {
    String name;
    String phonenumber;
    String  region;
    String address;
   String username;
    String password;
    Double balance;
    ArrayList<Order>CustomerOrders=new ArrayList<>();

    public Customer(String name, String phonenumber, String region, String address, String userame, String password, Double balance)
    {
        this.name = name;
        this.phonenumber = phonenumber;
        this.region = region;
        this.address = address;
        this.username = userame;
        this.password = password;
        this.balance = balance;
    }

    public String getregion()
    {
        return region;
    }

    public String getphonenumber()
    {
        return phonenumber;
    }

    public String getname()
    {
        return name;
    }
    public String getaddress()
    {
        return address;
    }
    public double getbalance()
    {
        return balance;
    }
    public String getusername()
    {
        return username;
    }
    public String  getpassword()
    {
        return password;
    }

  public String GetUserName()
  {
      return username;
  }
  public String GetPassword()
  {
      return password;
  }

}
