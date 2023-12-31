package com.OrdersandNotificationsManagement.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;


@RestController
@RequestMapping(value = "/api/products") // http://localhost:8080/products
public class ProductsController {
    ProductsRepository productsRepository = new ProductsRepository();
    /**
     * Retrieves a list of all products in the system.
     *
     * @return List of products if the user is logged in; otherwise, returns null.
     */
    @GetMapping("/all") // localhost:8080/products/all
    public List<Product> getAllProducts() {
        ArrayList<Product> Products = productsRepository.ViewRepo();
        if (com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus() == true) {
            return Products;
        }
        return null;
    }
    /**
     * Retrieves a product by its serial number.
     *
     * @param serialNumber The serial number of the product to be retrieved.
     * @return The product with the specified serial number if the user is logged in; otherwise, returns null.
     */
    @GetMapping("/{serialNumber}") // http://localhost:8080/products/1
    public Product getProductById(@PathVariable("serialNumber") int serialNumber) {
        if (com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus() == true) {
            Product chk = productsRepository.searchProduct(serialNumber);
            return chk;
        } else {
            return null;
        }
    }
}
