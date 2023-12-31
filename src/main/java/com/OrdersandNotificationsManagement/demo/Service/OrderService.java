package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Model.Channel.SMSChannel;
import com.OrdersandNotificationsManagement.demo.Model.Template.OrderPlacementTemplateEnglish;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;
import com.OrdersandNotificationsManagement.demo.Repos.OrderRepository;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;

import java.util.ArrayList;
import java.util.Map;


public class OrderService {

    /**
     * Creates a simple order for a customer with the given products and quantities.
     *
     * @param C             The customer placing the order.
     * @param OrderProducts Map of product names and their quantities in the order.
     * @return The ID of the created order if successful; -1 if the customer balance is insufficient.
     */
    public Integer makeSimpleOrder(Customer C, Map<String, Integer> OrderProducts) {
        SimpleOrder order = new SimpleOrder();
        double customerBalance = C.getbalance();
        ArrayList<String> orders = new ArrayList<>();
        for (String productName : OrderProducts.keySet()) {
            order.addProduct(com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.searchProduct(productName), OrderProducts.get(productName));
            order.setCustomerUsername(C.getusername());
            orders.add(productName);
        }

        double totalmoney = order.calcPrice();
        if (customerBalance < totalmoney) {
            return -1;
        }

        // Add new Notification to the queue
        Notification notification = new Notification(C, order, new SMSChannel(), new OrderPlacementTemplateEnglish(C.getusername(), orders));
        NotificationQueue.AddNotification(notification);

        C.setbalance(customerBalance - totalmoney);
        com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.addOrder(order);
        return order.getID();
    }
    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order with the specified ID, or null if not found.
     */
    public static Order GetOrder(Integer orderId) {
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderId);
        return order;
    }

    /**
     * Creates a compound order for a customer with the given orders from different users.
     *
     * @param C            The customer placing the compound order.
     * @param CompoundOrder Map of usernames and their corresponding orders.
     * @return The ID of the created compound order if successful; -1 if any customer balance is insufficient.
     */
    public Integer makeCompoundOrder(Customer C, Map<String, Map<String, Integer>> CompoundOrder) {
        double customerBalance = 0;
        CompoundOrder order = new CompoundOrder();
        order.setCustomerUsername(C.getusername());
        ArrayList<Notification> notifications = new ArrayList<>();
        for (String username : CompoundOrder.keySet()) {
            Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(username);
            Map<String, Integer> products = CompoundOrder.get(username);
            SimpleOrder simpleOrder = new SimpleOrder();
            for (String productName : products.keySet()) {
                Product product = ProductsRepository.searchProduct(productName);
                simpleOrder.addProduct(product, products.get(productName));
            }

            // Add notification for each customer
            ArrayList <String> productsNames = new ArrayList<>(products.keySet());
            Notification notification = new Notification(customer, simpleOrder, new SMSChannel(), new OrderPlacementTemplateEnglish(username, productsNames));
            notifications.add(notification);

            double totalmoney = simpleOrder.calcPrice();
            if (customer.getbalance() < totalmoney) {
                return -1;
            }
            customer.setbalance(customer.getbalance() - totalmoney);
            simpleOrder.setCustomerUsername(C.getusername());
            order.addOrder(simpleOrder);
            C.UpdateBalance(totalmoney);
        }

        // Add notifications into the notification queue
        for (Notification notification : notifications) {
            NotificationQueue.AddNotification(notification);
        }

        com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.addOrder(order);
        return order.getID();
    }

    /**
     * Cancels a simple order with the specified order ID.
     *
     * @param orderId The ID of the simple order to cancel.
     * @return true if the cancellation is successful; false otherwise.
     */
    public static boolean cancelSimpleOrder(Integer orderId) {
        for (Order order : OrderRepository.Orders) {
            if (order.getID().equals(orderId)) {
                // Return the price of the order to customer's balance again
                Customer customer = CustomersRepository.GetCustomer(order.getCutomerUsername());
                customer.setbalance(customer.getbalance() + order.calcPrice());
                Map<Product, Integer> OrderContent = order.getOrderDetails();
                com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.GetCanceledProducts(OrderContent);
                com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.removeOrder(order);
                return true;
            }
        }
        return false;
    }
    /**
     * Cancels a compound order with the specified order ID.
     *
     * @param orderId The ID of the compound order to cancel.
     * @return true if the cancellation is successful; false otherwise.
     */
    public static boolean cancelCompoundOrder(Integer orderId) {
        for (Order order : OrderRepository.Orders) {
            if (order.getID().equals(orderId)) {
                for (Order simpleOrder : ((CompoundOrder) order).getOrders()) {
                    cancelSimpleOrder(simpleOrder.getID());
                }
                com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.removeOrder(order);
                return true;
            }
        }
        return false;
    }

}
