package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class OrderPlacementTemplateEnglish implements TemplateStrategy{
    private String username;
    private List<String> productName;
    private String[] arguments;
    private final String s = "Dear {0} , your booking of the {1} is confirmed. thanks for using our store";
    public OrderPlacementTemplateEnglish(String username, List<String> productName){
        this.username = username;
        this.productName = productName;
        arguments = new String[]{username, String.join(", ",productName)};
    }
    public String getTemplateType(){
        return "OrderPlacementTemplateEnglish";
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
