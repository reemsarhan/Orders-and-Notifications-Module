package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Model.SimpleOrder;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;

import java.util.Map;


public class OrderService
{
    public Integer makeSimpleOrder(Customer C, Map<String, Integer> OrderProducts)
    {
        SimpleOrder order = new SimpleOrder();
        double customerBalance = C.getbalance();
        for (String productName : OrderProducts.keySet())
        {
            order.addProduct(com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.searchProduct(productName), OrderProducts.get(productName));
        }
        double totalmoney = order.calcPrice();
        if (customerBalance < totalmoney)
        {
            return -1;
        }
        C.setbalance(customerBalance - totalmoney);
        return order.getID();
    }
}
