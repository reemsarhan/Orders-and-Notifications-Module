package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Service.OrderService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.OrdersandNotificationsManagement.demo.Model.Region.Dokki;

@RestController
@RequestMapping("/makeOrder")


public class OrderContoroller {
    OrderService orderService = new OrderService();
    @PostMapping("/{userName}/{password}/simple")
    public Order makeSimpleOrder(@PathVariable("userName") String userName,@PathVariable("password") String password,  @RequestBody  Map<String, Integer> OrderProducts) {
        Response response = new Response();
        System.out.println("HERE");
        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userName,password);
        Integer OrderID = orderService.makeSimpleOrder(C, OrderProducts);
        if (OrderID == -1) {
            response.setStatus(false);
            response.setMessage("There is issue in making Order");
         //   return response;
        }
        response.setStatus(true);
        response.setMessage("Order made successfully, your order ID is: " + OrderID);
        return orderService.GetOrder(OrderID);
       // return response;
    }

    @PostMapping("/{userName}/{password}/compound")
    public ResponseEntity<?> makeCompoundOrder(@PathVariable("userName") String userName, @PathVariable("password") String password, @RequestBody Map<String ,Map<String, Integer>> CompoundOrder) {
        // Map Representation of Compound Order is as follows:
        // List<Username, List<ProductName, Quantity>>
        // Each key in the map is a username, and the value is order represented as a map of products and their quantities
        Response response = new Response();
        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userName,password);

        Integer OrderID = orderService.makeCompoundOrder(C, CompoundOrder);
        if (OrderID == -1)
        {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error placing order!");
        }
        return ResponseEntity.ok(orderService.GetOrder(OrderID));

    }
}
