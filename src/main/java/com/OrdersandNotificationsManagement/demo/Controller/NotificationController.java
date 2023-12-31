package com.OrdersandNotificationsManagement.demo.Controller;


import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Model.Channel.EmailChannel;
import com.OrdersandNotificationsManagement.demo.Model.Template.OrderPlacementTemplateEnglish;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;
import com.OrdersandNotificationsManagement.demo.Service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService = new NotificationService();

    @GetMapping("/send")
    public Response sendAllNotifications()   //http://localhost:8080/notification/send
    {
        return notificationService.sendAllNotifications();
    }

    @GetMapping("/view") //http://localhost:8080/notification/view
    public List<Notification> viewAllNotifications() {
        try {
            NotificationQueue.AddNotification(new Notification(
                    new Customer("Ahmed", "0100", Region.valueOf("Dokki"), "Cairo", "Tam", "Abdelrhman", 100.0),
                    new SimpleOrder(),
                    new EmailChannel(),
                    new OrderPlacementTemplateEnglish("Abdelrahman", new ArrayList<String>(Arrays.asList("Mobile", "Charger")))
            ));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        for (Notification n : notificationService.viewAllNotifications()) {
            System.out.println(n);
        }
        return notificationService.viewAllNotifications();
    }
}
