package com.OrdersandNotificationsManagement.demo.Model;

import java.time.LocalDateTime;
import java.util.Map;


public interface Order {

    public double calcPrice();

    public Integer getID();

    public Map<Product, Integer> getOrderDetails();

    public void ConfirmOrderShipping();

    public void CancelOrderShipping();

    public LocalDateTime getOrderShippingConfirmationDate();

    public Boolean getOrderShippingStatus();

    public String getCutomerUsername();
    public void setCustomerUsername(String username);

}
