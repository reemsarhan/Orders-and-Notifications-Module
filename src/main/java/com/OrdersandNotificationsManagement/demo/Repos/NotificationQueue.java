package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Model.Channel.EmailChannel;
import com.OrdersandNotificationsManagement.demo.Model.Template.*;
import org.w3c.dom.traversal.NodeIterator;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationQueue {
    public static ArrayList<Notification> notifications = new ArrayList<>();
    public static HashMap<String,Integer> notifiedEmailAddressFrequency = new HashMap<>();
    public static HashMap<String,Integer> templateTypeFrequency = new HashMap<>();
    public static void AddNotification(Notification notification){
        String s = notification.getCustomerPhone();
        // Keeps track of the most notified mobile number
        if (notifiedEmailAddressFrequency.containsKey(s)){
            notifiedEmailAddressFrequency.put(s,notifiedEmailAddressFrequency.get(s) + 1);
        }
        else{
            notifiedEmailAddressFrequency.put(s,1);
        }
        // keeps track of the most used template type
        String k = notification.getTemplateType();
        if (templateTypeFrequency.containsKey(k)){
            templateTypeFrequency.put(k,templateTypeFrequency.get(k) + 1);
        }
        else {
            templateTypeFrequency.put(k, 1);
        }
        // Add notification to the queue
        notifications.add(notification);
    }
    public static void RemoveNotification(Notification notification){
        notifications.remove(notification);
    }
    public static ArrayList<Notification> getNotifications(){
        return notifications;
    }
    public static void sendAllNotifications(){
        notifications.clear();
    }
}
