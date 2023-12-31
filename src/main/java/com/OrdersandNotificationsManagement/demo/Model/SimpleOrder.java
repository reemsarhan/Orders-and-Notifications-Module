package com.OrdersandNotificationsManagement.demo.Model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Testing notification controller
public class SimpleOrder implements Order {
    Map<Product, Integer> OrderProducts = new HashMap<>();
    public int id = 0;
    public static int idCounter = 0;
    public double TotalPrice = 0;
    public static boolean IsShipped = false;
    public LocalDateTime orderShippingConfirmationDate;
    public static String cutomerUsername;

    public LocalDateTime getOrderShippingConfirmationDate() {
        return orderShippingConfirmationDate;
    }

    public SimpleOrder() {
        id = ++idCounter;
    }

    public Boolean addProduct(Product p, int numOfProd) {
        if (p.totalComponents >= numOfProd) {
            OrderProducts.put(p, numOfProd);
            TotalPrice += (p.price * numOfProd);

            return true;
        }
        return false;
    }

    public void removeProduct(Product p) {
        if (OrderProducts.containsKey(p)) {
            OrderProducts.remove(p);
        }
    }


    public void setCustomerUsername(String username){
        cutomerUsername = username;
    }
    public String getCutomerUsername() {
        return cutomerUsername;
    }


    public double calcPrice() {
        return TotalPrice;
    }

    public Integer getID() {
        return id;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public Map<Product, Integer> viewOrder() {
        return OrderProducts;

    }

    public void ConfirmOrderShipping() {
        this.IsShipped = true;
        this.orderShippingConfirmationDate = LocalDateTime.now();
    }


    public void CancelOrderShipping() {
        this.IsShipped = false;
    }


    public Map<Product, Integer> getOrderDetails() {
        return OrderProducts;
    }

    public Boolean getOrderShippingStatus() {
        return IsShipped;
    }
}
