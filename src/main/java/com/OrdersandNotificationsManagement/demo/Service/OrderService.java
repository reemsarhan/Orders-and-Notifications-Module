package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Repos.OrderRepository;
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

        order.SetShippingTax(0.1);
        double totalmoney = order.calcPrice();
        if (customerBalance < totalmoney)
        {
            return -1;
        }
        C.setbalance(customerBalance - totalmoney);
        com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.addOrder(order);
        C.UpdateBalance(totalmoney);
        return order.getID();
    }

    public static Order GetOrder(Integer orderid)
    {
       Order order=com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderid);
        return order;

    }


    public Integer makeCompoundOrder(Customer C, Map<String, Map<String, Integer>> CompoundOrder)
    {

        double customerBalance = 0;
        CompoundOrder order = new CompoundOrder();
        for(String username : CompoundOrder.keySet())
        {
            Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(username);
            Map<String, Integer> products = CompoundOrder.get(username);
            SimpleOrder simpleOrder = new SimpleOrder();
            for(String productName : products.keySet())
            {
                Product product = ProductsRepository.searchProduct(productName);
                simpleOrder.addProduct(product, products.get(productName));
            }
            order.setShippingTax(0.07);
            double totalmoney = simpleOrder.calcPrice();
            if(customer.getbalance() < totalmoney)
            {
                return -1;
            }
            customer.setbalance(customer.getbalance() - totalmoney);
            order.addOrder(simpleOrder);
            C.UpdateBalance(totalmoney);
        }
        com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.addOrder(order);
        return order.getID();
    }

    public boolean cancelSimpleOrder(Integer orderId)
    {
        for(Order order: OrderRepository.Orders)
        {
            if(order.getID().equals(orderId))
            {
               Map<Product, Integer> OrderContnent=order.getOrderDetails();
               com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.GetCanceledProducts(OrderContnent);
               com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.removeOrder(order);
                return true;
            }
        }
        return false;
    }

    public boolean cancelCompoundOrder(Integer orderId)
    {
        for(Order order: OrderRepository.Orders)
        {
            if(order.getID().equals(orderId))
            {
                // cancel all simple orders inside the compound order
                for (Order simpleOrder : ((CompoundOrder) order).getOrders()){
                    cancelSimpleOrder(simpleOrder.getID());
                }
                com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.removeOrder(order);
            }
        }
        return false;
    }

    public boolean cancelSimpleOrderInsideCompound(Integer compoundId,Integer simpleId )
    {
//        for(Order order: OrderRepository.Orders)
//        {
//            if(order.getID().equals(orderId))
//            {
//                Map<Product, Integer> OrderContnent=order.getOrderDetails();
//                com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.GetCanceledProducts(OrderContnent);
//                com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.removeOrder(order);
//                return true;
//            }
//        }
       return false;
    }





}
