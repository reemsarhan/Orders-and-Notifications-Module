package com.OrdersandNotificationsManagement.demo.Model;

import com.OrdersandNotificationsManagement.demo.Model.Channel.Channel;
import com.OrdersandNotificationsManagement.demo.Model.Template.TemplateStrategy;
import com.OrdersandNotificationsManagement.demo.Repos.NotificationQueue;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("order")
    private Order order;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("template")
    private TemplateStrategy templateFormat;

    public Notification(Customer customer,Order order,Channel channel,TemplateStrategy templateFormat){
        this.customer = customer;
        this.order = order;
        this.channel = channel;
        this.templateFormat = templateFormat;
    }

    @Override
    public String toString() {
        String s = customer.getname() + " " + channel.getChannelName() + " " + templateFormat.getTemplate();
        return s;
    }
}
