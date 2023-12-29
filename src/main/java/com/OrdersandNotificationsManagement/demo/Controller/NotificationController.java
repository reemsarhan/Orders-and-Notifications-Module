package com.OrdersandNotificationsManagement.demo.Controller;


import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Notification")
public class NotificationController {
    private final NotificationService notificationService = new NotificationService();

    @GetMapping("/send")
    public Response sendAllNotifications(){
        return notificationService.sendAllNotifications();
    }
}
