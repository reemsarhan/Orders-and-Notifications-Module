package com.OrdersandNotificationsManagement.demo.Controller;


import com.OrdersandNotificationsManagement.demo.Model.Channel.EmailChannel;
import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.Notification;
import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Model.SimpleOrder;
import com.OrdersandNotificationsManagement.demo.Model.Template.UsernameProductTemplateEnglish;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;
import com.OrdersandNotificationsManagement.demo.Service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService = new NotificationService();

    @GetMapping("/send")
    public Response sendAllNotifications(){
        return notificationService.sendAllNotifications();
    }
    @GetMapping("/view")
    public List<Notification> viewAllNotifications(){
        try {
            NotificationQueue.AddNotification(new Notification(
                    new Customer("ahmed", "0100", "cairo", "tam", "abdelrhman", "abd", 100.0),
                    new SimpleOrder() ,
                    new EmailChannel(),
                    new UsernameProductTemplateEnglish("abdelrhman","Mobile Charger")
            ));
        }catch (Exception e){
            System.out.println(e.toString());
        }
        for (Notification n: notificationService.viewAllNotifications()) {
            System.out.println(n);
        }
        return notificationService.viewAllNotifications();
    }
}
