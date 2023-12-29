package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;
import java.util.Objects;

public class UsernameProductTemplateFrench implements TemplateStrategy{
    private String username;
    private String productName;
    private String[] arguments;
    private final String s = "Cher {0}, votre réservation du {1} est confirmée. merci d'utiliser notre magasin :)";
    public UsernameProductTemplateFrench(String username,String productName){
        this.username = username;
        this.productName = productName;
        arguments = new String[]{username, productName};
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
