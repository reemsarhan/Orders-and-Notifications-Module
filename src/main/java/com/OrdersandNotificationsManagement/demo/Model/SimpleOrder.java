package com.OrdersandNotificationsManagement.demo.Model;

import java.util.HashMap;
import java.util.Map;

// Testing notification controller
public class SimpleOrder implements Order
{
    Map<Product, Integer> OrderProducts = new HashMap<>();
    public int id = 0;
    public static int idCounter = 0;
    public double TotalPrice = 0;
    public double ShippingTax = 0;

    public SimpleOrder()
    {
        id = ++idCounter;
    }

    public Boolean addProduct(Product p, int numOfProd)
    {
        if (p.totalComponents >= numOfProd)
        {
            OrderProducts.put(p, numOfProd);
            TotalPrice += (p.price * numOfProd);
            return true;
        }
        return false;
    }

    public void removeProduct(Product p)
    {
        if (OrderProducts.containsKey(p))
        {
            OrderProducts.remove(p);
        }
    }

    public void SetShippingTax(double val)
    {
        ShippingTax = val;
    }

    public double calcPrice()
    {
        double ShippingTax = TotalPrice * this.ShippingTax;
        return (TotalPrice + ShippingTax);
    }

    public Integer getID()
    {
        return id;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public Map<Product, Integer> viewOrder()
    {
       return OrderProducts;

    }

}
