package com.OrdersandNotificationsManagement.demo.Model.Channel;

import com.OrdersandNotificationsManagement.demo.Model.Channel.Channel;

public class EmailChannel extends Channel {

    @Override
    public String getChannelName() {
        return "Via Email";
    }
}
