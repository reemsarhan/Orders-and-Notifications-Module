package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.Category;
import com.OrdersandNotificationsManagement.demo.Model.Product;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Map;

public class ProductsRepository implements Repository
{
    public static ArrayList<Product> Products = new ArrayList<>();
    public ProductsRepository()
    {
        Category c1 = new Category("FOOD", 1);
        // Product p1 = new Product(c1, 1, "Product1", "V1", 1, 20);

        Category food = new Category("FOOD", 10);
        Category drinks = new Category("DRINKS", 20);
        Category electronics = new Category("ELECTRONICS", 30);

        Product p1 = new Product(food, 1, "Apple", "V1", 100, 20);
        Product p2 = new Product(food,2 , "Banana", "V1", 2, 35);
        Product p3 = new Product(food, 3, "Watermelon", "V1", 200, 20);

        Products.add(p1);
        Products.add(p2);
        Products.add(p3);


    }

    @Override
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

    public static Product searchProduct(Integer serialNumber)
    {
        for (Product p:Products)
        {
            if(p.getSerialnumber().equals(serialNumber))
                return p;
        }
        return null;
    }

    public static Product searchProduct(String productName)
    {
        for (Product p:Products)
        {
            if(p.getName().equals(productName))
                return p;
        }
        return null;
    }

    public static void GetCanceledProducts(Map<Product,Integer>Products)
    {
        for (Product product : Products.keySet())
        {
            product.setTotalComponents(product.getTotalComponents() + Products.get(product));
        }
    }
}
