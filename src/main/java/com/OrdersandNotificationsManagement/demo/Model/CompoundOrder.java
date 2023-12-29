package com.OrdersandNotificationsManagement.demo.Model;

import java.util.ArrayList;

public class CompoundOrder implements Order {
    double totalPrice = 0;
    private ArrayList<SimpleOrder> orders = new ArrayList<>();

    public ArrayList<SimpleOrder> getOrders() {
        return orders;
    }

    public void addOrder(SimpleOrder order) {
        orders.add(order);
    }

    public double calcPrice() {
        for (SimpleOrder order : orders) {
            order.setTaxvalue(0.10);
            order.calcPrice();
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }
}
