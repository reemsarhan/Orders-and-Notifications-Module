package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;

public class OrderShipmentTemplateFrench implements TemplateStrategy{
    private String username;
    private Object[] arguments;
    private int id;
    private final String s = "Cher {0}, L'ordre que vous avez passée a été expédiée et est en attente de livraison. OrderId: {1}";
    public OrderShipmentTemplateFrench(String username,int id){
        this.username = username;
        this.id = id;
        arguments = new Object[]{username,id};
    }
    public String getTemplateType(){
        return "OrderShipmentTemplateFrench";
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
