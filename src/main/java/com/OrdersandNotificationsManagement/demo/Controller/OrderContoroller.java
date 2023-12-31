package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Model.*;
import com.OrdersandNotificationsManagement.demo.Repos.OrderRepository;
import com.OrdersandNotificationsManagement.demo.Service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/order")

public class OrderContoroller {
    OrderService orderService = new OrderService();
    OrderRepository OrderRepository = new OrderRepository();

    /**
     * Creates a simple order for a customer with the given products and quantities.
     *
     * @param userData      User authentication data including username and password.
     * @param OrderProducts Map of product names and their quantities in the order.
     * @return ResponseEntity with the created order or an error message.
     */
    @PostMapping("/make/simple")
    public ResponseEntity<?> makeSimpleOrder(@RequestHeader Map<String, String> userData, @RequestBody Map<String, Integer> OrderProducts) {
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userData.get("username"), userData.get("password"));
        // check if the customer sent wrong data while creating the order
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User authentication failed, please enter correct data!");
        }
        Integer OrderID = orderService.makeSimpleOrder(customer, OrderProducts);
        if (OrderID == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error placing order!");
        }
        // Decrease the quantity of each product in the order from the database
        for (String productName : OrderProducts.keySet()) {
            Product product = com.OrdersandNotificationsManagement.demo.Repos.ProductsRepository.searchProduct(productName);
            product.setTotalComponents(product.getTotalComponents() - OrderProducts.get(productName));
        }
        Order order=com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(OrderID);
        order.setCustomerUsername(userData.get("username"));
        return ResponseEntity.ok(orderService.GetOrder(OrderID));
        //  return orderService.GetOrder(OrderID);
    }
    /**
     * Creates a compound order for a customer with the given orders from different users.
     *
     * @param userData      User authentication data including username and password.
     * @param CompoundOrder Map of usernames and their corresponding orders.
     * @return ResponseEntity with the created order or an error message.
     */
    @PostMapping("/make/compound")
    public ResponseEntity<?> makeCompoundOrder(@RequestHeader Map<String, String> userData, @RequestBody Map<String, Map<String, Integer>> CompoundOrder) {
        /* Map Representation of Compound Order is as follows:
           List<Username, List<ProductName, Quantity>>
           Each key in the map is a username, and the value is order represented as a map of products and their quantities
        */
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getCustomer(userData.get("username"), userData.get("password"));
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User authentication failed, please enter correct data!");
        }
        ArrayList<String> notFoundCustomers = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.getNotFoundCustomers(new ArrayList<String>(CompoundOrder.keySet()));
        if (notFoundCustomers != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong usernames: " + notFoundCustomers + "please enter correct data!");
        }

        /* //handle region part
        ArrayList<String> userNames = new ArrayList<String>(CompoundOrder.keySet());
        Region region = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(userNames.get(0)).getregion();
        for (String customername : userData.keySet()) {
            if (com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(customername).getregion() != region) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Compound Order can't be made to Customers of different regions!");
            }
        } */

        Integer OrderID = orderService.makeCompoundOrder(customer, CompoundOrder);
        if (OrderID == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error placing order!");
        }
        return ResponseEntity.ok(orderService.GetOrder(OrderID));
    }
    /**
     * Cancels a simple order with the specified username and order ID.
     *
     * @param userName The username of the customer.
     * @param orderID  The ID of the order to be cancelled.
     * @return ResponseEntity indicating the success or failure of the cancellation.
     */
    @PostMapping("/cancel/0/{userName}/{orderID}")
    public ResponseEntity<?> CancelSimpleOrder(@PathVariable("userName") String userName, @PathVariable("orderID") Integer orderID) {

        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(userName);
        if (C != null) {
            Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderID);
            if(order == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found!");
            }
            boolean status = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(orderID).getOrderShippingStatus();
            if (status) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is in shipping status please cancel shipping first!");
            }
            if (orderService.cancelSimpleOrder(orderID)) {
                return ResponseEntity.ok("Order Cancelled Successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in cancelling Order!");
    }
    /**
     * Cancels a compound order with the specified username and compound order ID.
     *
     * @param userName        The username of the customer.
     * @param compoundOrderID The ID of the compound order to be cancelled.
     * @return ResponseEntity indicating the success or failure of the cancellation.
     */

    @PostMapping("/cancel/1/{userName}/{compoundOrderID}")
    public ResponseEntity<?> CancelCompoundOrder(@PathVariable("userName") String userName, @PathVariable("compoundOrderID") Integer compoundOrderID) {

        Customer C = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(userName);
        if (C != null) {
            Order order = com.OrdersandNotificationsManagement.demo.Repos.OrderRepository.GetOrder(compoundOrderID);
            if(order == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found!");
            }
            boolean status = order.getOrderShippingStatus();
            if (status) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is in shipping status please cancel shipping first!");
            }
            if (orderService.cancelCompoundOrder(compoundOrderID)) {
                return ResponseEntity.ok("Order Cancelled Successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in cancelling Order!");
    }


    /**
     * Retrieves all orders in the system.
     *
     * @return List of orders if the user is logged in; otherwise, returns null.
     */
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        ArrayList<Order> orders = OrderRepository.ViewRepo();
        if (com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse.GetStatus() == true) {
            return orders;
        }
        return null;
    }
}
