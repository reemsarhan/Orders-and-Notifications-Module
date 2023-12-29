package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.Category;
import com.OrdersandNotificationsManagement.demo.Model.Product;

import java.util.ArrayList;

public class ProductsRepository {
    public static ArrayList<Product> Products = new ArrayList<>();
    public ProductsRepository() {
        Category c1 = new Category("C", 1);
        Product p1 = new Product(c1, 1, "Product1", "V1", 200);
        Products.add(p1);
    }

    public ArrayList<Product> ViewRepo()
    {
        return Products;
    }
    public static void addProduct (Product p)
    {
        Products.add(p);
    }

    public static void removeProduct (Product p)
    {
        Products.remove(p);
    }
    public static boolean isProductFound (Integer serialNumber)
    {
        for (Product p:Products)
        {
            if(p.getSerialnumber().equals(serialNumber))
                return true;
        }
        return false;
    }
}
