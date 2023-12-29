package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Service.OrderService;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import static com.OrdersandNotificationsManagement.demo.Model.Region.Dokki;

@RestController
@RequestMapping("/makeOrder")


public class OrderContoroller {
    OrderService orderService = new OrderService();
    @PostMapping("/{userName}/{password}/simple")
    public Response makeSimpleOrder(@PathVariable("userName") String userName,@PathVariable("password") String password,  @RequestBody Map<String, Integer> OrderProducts) {
        Response response = new Response();
        System.out.println("HERE");
        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userName,password);
        Integer OrderID = orderService.makeSimpleOrder(C, OrderProducts);
        if (OrderID == -1) {
            response.setStatus(false);
            response.setMessage("There is issue in making Order");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Order made successfully, your order ID is: " + OrderID);
        return response;
    }
}
