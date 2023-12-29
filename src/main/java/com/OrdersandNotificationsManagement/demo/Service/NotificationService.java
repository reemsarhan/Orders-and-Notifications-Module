package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Notification;
import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;

public class NotificationService {
    public Response addNotification(Notification notification){
        NotificationQueue.AddNotification(notification);
        return new Response();
    }
    public Response sendAllNotifications(){
        NotificationQueue.sendAllNotifications();
        return new Response();
    }
}
