package com.OrdersandNotificationsManagement.demo.Model.Template;

import java.text.MessageFormat;

public class ProductTemplateEnglish implements TemplateStrategy{
    private String username;
    private String[] arguments;
    private final String s = "Cher {0}, Nous espérons que vous passez une bonne journée. Votre commande est confirmée et est en attente d'expédition. Merci!";
    public ProductTemplateEnglish(String username){
        this.username = username;
        arguments = new String[]{username};
    }
    @Override
    public String getTemplate() {
        return MessageFormat.format(s,arguments);
    }
}
