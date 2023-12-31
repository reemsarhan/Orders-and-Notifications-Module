package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Channel.SMSChannel;
import com.OrdersandNotificationsManagement.demo.Model.CompoundOrder;
import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.Notification;
import com.OrdersandNotificationsManagement.demo.Model.Order;
import com.OrdersandNotificationsManagement.demo.Model.Template.OrderShipementTemplateEnglish;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShippingService {

    /**
     * The duration (in minutes) within which an order can be canceled after shipping confirmation.
     */
    public static final int CANCELLATION_DURATION_MINUTES = 5;

    /**
     * Confirms shipping for a simple order with the specified order ID.
     *
     * @param orderId      The ID of the simple order to confirm shipping.
     * @param shippingFees The shipping fees to be deducted from the customer's balance.
     * @return true if the shipping is confirmed successfully; false otherwise.
     */
    public Boolean confirmShippingSimpleOrder(Integer orderId, double shippingFees) {
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderId);
        /*
         * Check if the order exists and if it has confirmed shipping before then we return false
         */
        if (order == null || order.getOrderShippingStatus() == true) {
            return false;
        }
        /*
         * Check if the customer has enough balance to pay the shipping fees of the order
         * if the customer doesn't have enough balance we return false
         */
        Customer orderCustomer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(order.getCutomerUsername());
        if (orderCustomer.getbalance() < shippingFees) {
            return false;
        }

        // Add notification to notification queue for shipment
        Notification notification = new Notification(orderCustomer, order, new SMSChannel(), new OrderShipementTemplateEnglish(orderCustomer.getname(), orderId));
        NotificationQueue.AddNotification(notification);

        /*
         * deduct the shipping fees from the customer balance then confirm the shipping of the order
         * */
        orderCustomer.setbalance(orderCustomer.getbalance() - shippingFees);
        order.ConfirmOrderShipping();
        return true;
    }
    /**
     * Confirms shipping for a compound order with the specified order ID.
     *
     * @param orderID      The ID of the compound order to confirm shipping.
     * @param shippingFees The shipping fees to be deducted from each customer's balance.
     * @return true if the shipping is confirmed successfully; false otherwise.
     */

    public Boolean confirmShippingCompoundOrder(Integer orderID, double shippingFees) {
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderID);
        /*
         * Check if the order exists and if it has confirmed shipping before then we return false
         */
        if (order == null || order.getOrderShippingStatus() == true) {
            return false;
        }
        /*
         * Check if all the customers of the compound order have enough balance to pay the shipping fees
         * */
        for (Order simpleOrder : ((CompoundOrder) order).getOrders()) {
            Customer orderCustomer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(order.getCutomerUsername());
            if (orderCustomer.getbalance() < shippingFees) {
                return false;
            }
        }

        /*
         * Deduct the shipping fees from the customers balance then confirm the shipping of each simple order in the compound order
         * */
        ArrayList<Notification> notifications = new ArrayList<>();
        for (Order simpleOrder : ((CompoundOrder) order).getOrders()) {
            Customer orderCustomer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(order.getCutomerUsername());
            orderCustomer.setbalance(orderCustomer.getbalance() - shippingFees);
            simpleOrder.ConfirmOrderShipping();

            // Add notification for each customer
            Notification notification = new Notification(orderCustomer, simpleOrder, new SMSChannel(), new OrderShipementTemplateEnglish(orderCustomer.getname(), orderID));
            NotificationQueue.AddNotification(notification);
        }

        order.ConfirmOrderShipping();
        return true;
    }
    /**
     * Cancels shipping for a simple order with the specified order ID.
     *
     * @param orderId      The ID of the simple order to cancel shipping.
     * @param shippingFees The shipping fees to be returned to the customer's balance.
     * @return true if the shipping is canceled successfully; false otherwise.
     */

    public Boolean cancelSimpleShipping(Integer orderId, double shippingFees) {
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderId);
        if (order == null || !orderCanBeCanceled(orderId)) {
            return false;
        }
        String username = order.getCutomerUsername();
        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(username);
        C.setbalance(C.getbalance() + shippingFees);
        order.CancelOrderShipping();
        return true;
    }
    /**
     * Cancels shipping for a compound order with the specified order ID.
     *
     * @param orderID      The ID of the compound order to cancel shipping.
     * @param shippingFees The shipping fees to be returned to each customer's balance.
     * @return true if the shipping is canceled successfully; false otherwise.
     */
    public Boolean cancelCompoundShipping(Integer orderID, double shippingFees) {
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderID);
        if (order == null || !orderCanBeCanceled(orderID)) {
            return false;
        }
        for (Order simpleOrder : ((CompoundOrder) order).getOrders()) {
            cancelSimpleShipping(simpleOrder.getID(), shippingFees);
        }
        order.CancelOrderShipping();
        return true;
    }

    /**
     * Checks if an order can be canceled based on the cancellation duration.
     *
     * @param orderID The ID of the order to check.
     * @return true if the order can be canceled; false otherwise.
     */
    public Boolean orderCanBeCanceled(Integer orderID) {
        LocalDateTime currentTime = LocalDateTime.now();
        Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderID);
        if (order.getOrderShippingStatus() == false) {
            return false;
        } else {
            return order.getOrderShippingConfirmationDate().plusMinutes(CANCELLATION_DURATION_MINUTES).isAfter(currentTime);
        }
    }


}
