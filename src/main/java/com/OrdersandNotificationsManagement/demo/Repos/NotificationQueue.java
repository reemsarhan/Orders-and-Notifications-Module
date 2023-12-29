package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Model.Channel.EmailChannel;
import com.OrdersandNotificationsManagement.demo.Model.Template.UsernameProductTemplateEnglish;
import org.w3c.dom.traversal.NodeIterator;

import java.util.ArrayList;

public class NotificationQueue {
    public static ArrayList<Notification> notifications = new ArrayList<>();
    public static void AddNotification(Notification notification){
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
