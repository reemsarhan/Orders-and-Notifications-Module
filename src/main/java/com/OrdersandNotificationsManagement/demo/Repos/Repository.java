package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.Product;

import java.util.ArrayList;

public interface Repository<T>
{
    ArrayList<T> ViewRepo();
}
