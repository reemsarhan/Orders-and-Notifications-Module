package com.OrdersandNotificationsManagement.demo.Model;

public class Product {
    Category category;
    Integer serialnumber;
    String name;
    String vendor;
    double price;
    Integer totalComponents;

    public Product(Category category, Integer serialnumber, String name, String vendor, double price, Integer totalComponents) {
        this.category = category;
        this.serialnumber = serialnumber;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.totalComponents = totalComponents;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getSerialnumber() {
        return serialnumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public double getPrice() {
        return price;
    }

    public Integer gettotalComponents()
    {
        return totalComponents;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSerialnumber(Integer serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void settotalComponents(Integer totalComponents) {
        this.totalComponents = totalComponents;
    }

    public void updatetotalComponents(Integer DecComponents)
    {
        totalComponents-=DecComponents;
    }
    public String toString() {
        return "Product{" +
                "category=" + category +
                ", serialnumber=" + serialnumber +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", price=" + price + '\'' +
                ", totalComponents=" + totalComponents + '\'' +
                '}';

    }
}
