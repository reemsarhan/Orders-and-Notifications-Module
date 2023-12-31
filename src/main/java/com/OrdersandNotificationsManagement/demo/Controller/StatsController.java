package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @GetMapping("/phone")
    public String getMostNotifiedMobileNumber() { // http://localhost:8080/api/stats/phone
        String mx = null;
        int maxValue = 0;
        HashMap<String, Integer> map = NotificationQueue.notifiedEmailAddressFrequency;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                mx = entry.getKey();
            }
        }
        String ret="Most recently used Phone number is "+mx+" ";
        return ret;
    }

    @GetMapping("/template")
    public String getMostUsedTemplate() { // http://localhost:8080/api/stats/template
        String mx = null;
        int maxValue = 0;
        HashMap<String, Integer> map = NotificationQueue.templateTypeFrequency;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                mx = entry.getKey();
            }
        }
        String res="Most recently used Temp is "+mx+" ";
        return res;
    }
}
