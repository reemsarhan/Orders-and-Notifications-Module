package com.OrdersandNotificationsManagement.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

// Testing notification controller
public class SimpleOrder implements Order
{
    Map<Product, Integer> OrderProducts = new HashMap<>();
    @JsonProperty("id")
    public static int id = 0;
    public double TotalPrice = 0;
    public static double taxvalue = 0;

    public SimpleOrder()
    {
        id = ++id;
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

    public static void setTaxvalue(double val)
    {
        taxvalue = val;
    }

    public double calcPrice()
    {
        double ShippingTax = TotalPrice * taxvalue;
        return (TotalPrice + ShippingTax);
    }

    public Integer getID()
    {
        return id;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

}
