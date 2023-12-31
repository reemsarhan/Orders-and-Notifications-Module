package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Notification;
import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;

import java.util.ArrayList;

public class NotificationService {
    /**
     * Adds a new notification to the notification queue.
     *
     * @param notification The notification to be added.
     * @return A Response object indicating the result of the operation.
     */
    public Response addNotification(Notification notification){
        NotificationQueue.AddNotification(notification);
        return new Response();
    }
    /**
     * Sends all notifications in the notification queue.
     *
     * @return A Response object indicating the result of the operation.
     */
    public Response sendAllNotifications(){
        NotificationQueue.sendAllNotifications();
        return new Response();
    }
    /**
     * Retrieves a list of all notifications in the notification queue.
     *
     * @return An ArrayList of Notification objects representing all notifications in the queue.
     */
    public ArrayList<Notification> viewAllNotifications(){
        return NotificationQueue.getNotifications();
    }
}
