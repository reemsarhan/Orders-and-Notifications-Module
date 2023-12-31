package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class OrderPlacementTemplateFrench implements TemplateStrategy{
    private String username;
    private List<String> productName;
    private String[] arguments;
    private final String s = "Cher {0}, votre réservation du {1} est confirmée. merci d'utiliser notre magasin :)";
    public OrderPlacementTemplateFrench(String username,List<String> productName){
        this.username = username;
        this.productName = productName;
        arguments = new String[]{username, String.join(", ",productName)};
    }
    public String getTemplateType(){
        return "OrderPlacementProductTemplateFrench";
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
