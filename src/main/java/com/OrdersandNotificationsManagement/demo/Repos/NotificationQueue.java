package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.Notification;

import java.util.ArrayList;

public class NotificationQueue {
    public static ArrayList<Notification> notifications;
    public static void AddNotification(Notification notification){
        notifications.add(notification);
    }
    public static void RemoveNotification(Notification notification){
        notifications.remove(notification);
    }
    public static void sendAllNotifications(){
        notifications.clear();
    }
}
