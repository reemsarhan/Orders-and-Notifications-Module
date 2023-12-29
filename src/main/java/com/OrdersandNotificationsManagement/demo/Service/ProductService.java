package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;

public class ProductService {
    ProductsRepository productsRepository = new ProductsRepository();

    public Boolean addProduct(Product P) {
        try {
            if (productsRepository.isProductFound(P.getSerialnumber())) {
                System.out.println("Product already exists.");
                return false;
            } else {
                productsRepository.addProduct(P);
            }
        } catch (Exception e) {
            System.out.println("Exception in adding product: " + e.getMessage());
            return false;
        }
        return true;
    }
}
