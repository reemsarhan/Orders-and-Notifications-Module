package com.OrdersandNotificationsManagement.demo.Model;

public class Product
{
    Category category;
    Integer serialnumber;
    String name;
    String vendor;
    double price;
    public Product(Category category, Integer serialnumber, String name, String vendor, double price) {
        this.category = category;
        this.serialnumber = serialnumber;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
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

    public String toString() {
        return "Product{" +
                "category=" + category +
                ", serialnumber=" + serialnumber +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", price=" + price +
                '}';
    }
}
