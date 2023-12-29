package com.OrdersandNotificationsManagement.demo.Model;

public class Category {
    String name;
    Integer totalnumberofproducts;

    public Category(String name, Integer totalnumberofproducts) {
        this.name = name;
        this.totalnumberofproducts = totalnumberofproducts;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalnumberofproducts() {
        return totalnumberofproducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalnumberofproducts(Integer totalnumberofproducts) {
        this.totalnumberofproducts = totalnumberofproducts;
    }

    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", totalnumberofproducts=" + totalnumberofproducts +
                '}';
    }

}
