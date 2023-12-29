package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.*;

import java.util.ArrayList;

public class OrderRepository implements Repository
{
    public static ArrayList<Order> Orders = new ArrayList<>();
    @Override
    public ArrayList<Order> ViewRepo()
    {
        return Orders;
    }

    public static void addOrder(Order O)
    {
        Orders.add(O);
    }

    public static void removeOrder(Order O)
    {
        Orders.remove(O);
    }


}
