package com.OrdersandNotificationsManagement.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.OrdersandNotificationsManagement.demo.Model.Product;
import com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository;


@RestController
@RequestMapping(value = "/products")
public class ProductsController
{
    ProductsRepository productsRepository = new ProductsRepository();

    @GetMapping("/all")
    public List<Product> getAllProducts()
    {
        ArrayList<Product> Products = productsRepository.ViewRepo();
        if(com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus()==true)
        {
            return Products;
        }
        //i want to return to api message that he has to login first
      return null;
    }

    @GetMapping("/{serialNumber}") // http://localhost:8080/products/1
    public Product getProductById(@PathVariable("serialNumber") int serialNumber) {
        if (com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus() == true)
        {
            Product chk = productsRepository.iProductFound(serialNumber);
            return chk;
        }
        else
        {
            return null;
        }
    }
}
