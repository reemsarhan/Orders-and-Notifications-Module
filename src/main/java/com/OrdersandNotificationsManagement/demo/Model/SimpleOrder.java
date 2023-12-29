package com.OrdersandNotificationsManagement.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

// Testing notification controller
public class SimpleOrder implements Order{
    @JsonProperty("id")
    private final int id = 1;
}
