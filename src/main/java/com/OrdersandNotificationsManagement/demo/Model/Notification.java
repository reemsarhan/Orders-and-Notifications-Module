package com.OrdersandNotificationsManagement.demo.Model;

import com.OrdersandNotificationsManagement.demo.Model.Channel.Channel;
import com.OrdersandNotificationsManagement.demo.Model.Template.TemplateStrategy;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;

public class Notification {
    private Customer customer;
    private Order order;
    private Channel channel;
    private TemplateStrategy templateFormat;

    public Notification(Customer customer,Order order,Channel channel,TemplateStrategy templateFormat){
        this.customer = customer;
        this.order = order;
        this.channel = channel;
        this.templateFormat = templateFormat;
    }
}
