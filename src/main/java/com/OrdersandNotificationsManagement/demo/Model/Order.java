package com.OrdersandNotificationsManagement.demo.Model;

import java.util.ArrayList;
import java.util.Map;


public interface Order
{

    public double calcPrice();

    public Integer getID();

    public Map<Product,Integer> getOrderDetails();

}
