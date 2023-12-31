package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;

public class ProductService {
    ProductsRepository productsRepository = new ProductsRepository();

    /**
     * Adds a new product to the system.
     *
     * @param P The product to be added.
     * @return true if the product is added successfully; false if the product already exists or an exception occurs.
     */
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
