package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;

public class OrderShipementTemplateEnglish implements TemplateStrategy{
    private String username;
    private int id;
    private Object[] arguments;
    private final String s = "Dear {0}, The order you placed is shipped and waiting to be delivered. OrderId: {1}";
    public OrderShipementTemplateEnglish(String username,int id){
        this.username = username;
        this.id = id;
        arguments = new Object[]{username,id};
    }
    public String getTemplateType(){
        return "OrderShipementTemplateEnglish";
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
