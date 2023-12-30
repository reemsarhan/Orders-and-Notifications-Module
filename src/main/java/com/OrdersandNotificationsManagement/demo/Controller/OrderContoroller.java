package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Repos.OrderRepository;
import com.OrdersandNotificationsManagement.demo.Service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/order")

public class OrderContoroller
{
    OrderService orderService = new OrderService();
    OrderRepository OrderRepository = new OrderRepository();
    @PostMapping("/make/simple")
    public  ResponseEntity<?> makeSimpleOrder(@RequestHeader Map<String, String> userData, @RequestBody Map<String, Integer> OrderProducts) {
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userData.get("username"), userData.get("password"));
        // check if the customer sent wrong data while creating the order
        if(customer == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User authentication failed, please enter correct data!");
        }
        Integer OrderID = orderService.makeSimpleOrder(customer, OrderProducts);
        if (OrderID == -1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error placing order!");
        }
        // Decrease the quantity of each product in the order from the database
        for (String productName : OrderProducts.keySet()) {
            Product product = com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.searchProduct(productName);
            product.setTotalComponents(product.getTotalComponents() - OrderProducts.get(productName));
        }
        return ResponseEntity.ok(orderService.GetOrder(OrderID));
        //  return orderService.GetOrder(OrderID);
    }

    @PostMapping("/make/compound")
    public ResponseEntity<?> makeCompoundOrder(@RequestHeader Map<String, String> userData, @RequestBody Map<String, Map<String, Integer>> CompoundOrder) {
        /* Map Representation of Compound Order is as follows:
           List<Username, List<ProductName, Quantity>>
           Each key in the map is a username, and the value is order represented as a map of products and their quantities
        */
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userData.get("username"), userData.get("password"));
        if (customer == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User authentication failed, please enter correct data!");
        }
        ArrayList <String> notFoundCustomers = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getNotFoundCustomers(new ArrayList<String>(CompoundOrder.keySet()));
        if(notFoundCustomers != null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong usernames: " + notFoundCustomers + "please enter correct data!");
        }
        Integer OrderID = orderService.makeCompoundOrder(customer, CompoundOrder);
        if (OrderID == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error placing order!");
        }
        return ResponseEntity.ok(orderService.GetOrder(OrderID));
    }

    @PostMapping("/cancel/{userName}/{orderID}")
    public ResponseEntity<?> CancelSimpleOrder(@PathVariable("userName") String userName,@PathVariable("orderID") Integer orderID)
    {

         Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(userName);
         if(C!=null)
         {
             if (orderService.cancelSimpleOrder(orderID))
             {
                 return ResponseEntity.ok("OrderCancelled Successfully");
             }
         }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in cancelling Order!");
    }

    @GetMapping("/all")
    public List<Order> getAllOrders()
    {
        ArrayList<Order> orders = OrderRepository.ViewRepo();
        if(com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus()==true)
        {
            return orders;
        }
        return null;
    }
}
