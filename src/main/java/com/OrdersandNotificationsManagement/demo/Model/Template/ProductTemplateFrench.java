package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;

public class ProductTemplateFrench implements TemplateStrategy{
    private String username;
    private String[] arguments;
    private final String s = "Dear {0} ,We hope you are having a great day, your product is confirmed. Thank You!";
    public ProductTemplateFrench(String username){
        this.username = username;
        arguments = new String[]{username};
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
