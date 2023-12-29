package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;
import java.util.Objects;

public class UsernameProductTemplateEnglish implements TemplateStrategy{
    private String username;
    private String productName;
    private String[] arguments;
    private final String s = "Dear {0} , your booking of the {1} is confirmed. thanks for using our store";
    public UsernameProductTemplateEnglish(String username,String productName){
        this.username = username;
        this.productName = productName;
        arguments = new String[]{username, productName};
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
