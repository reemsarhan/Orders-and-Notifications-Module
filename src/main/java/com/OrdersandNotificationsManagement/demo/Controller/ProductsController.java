package com.OrdersandNotificationsManagement.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;


@RestController
@RequestMapping(value = "/products")
public class ProductsController {
    ProductsRepository productsRepository = new ProductsRepository();

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        ArrayList<Product> Products = productsRepository.ViewRepo();
        return Products;
    }

    @GetMapping("/{serialNumber}")
    public boolean getProductById(@PathVariable("serialNumber") int serialNumber) {
        Boolean chk = productsRepository.isProductFound(serialNumber);
        return chk;
    }
}
