package com.OrdersandNotificationsManagement.demo.Controller;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OrdersandNotificationsManagement.demo.Service.ShippingService;

@RestController

@RequestMapping("/api/shipping")

public class ShippingController {

    public static final double SIMPLE_SHIPPING_FEES = 30;
    public static final double COMPOUND_SHIPPING_FEES = 20;
    ShippingService shippingService = new ShippingService();

    @PostMapping("/confirmSimple/{orderID}")
    public ResponseEntity<?> confirmShippingSimpleOrder(@PathVariable("orderID") Integer orderID) {
        Boolean status = shippingService.confirmShippingSimpleOrder(orderID, SIMPLE_SHIPPING_FEES);
        if (status) {
            return ResponseEntity.ok("Shipment for order: " + orderID + " has been confirmed");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in confirming the shipping!");

        }
    }

    @PostMapping("/confirmCompound/{orderID}")
    public ResponseEntity<?> confirmShippingCompoundOrder(@PathVariable("orderID") Integer orderID) {
        Boolean status = shippingService.confirmShippingCompoundOrder(orderID, COMPOUND_SHIPPING_FEES);
        if (status) {
            return ResponseEntity.ok("Shipment for order: " + orderID + " has been confirmed");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in confirming the shipping!");

        }
    }

    @PostMapping("/cancel/simple/{username}/{orderID}")
    public ResponseEntity<?> cancelSimpleOrderShipping(@PathVariable("username") String username, @PathVariable("orderID") Integer orderID) {
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(username);
        // check if the customer sent wrong data while creating the order
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is wrong, please enter correct data!");
        }
        boolean status = shippingService.cancelSimpleShipping(orderID, SIMPLE_SHIPPING_FEES);
        if (status) {
//            com.OrdersandNotificationsManagement.demo.Service.OrderService.cancelSimpleOrder(orderID);
            return ResponseEntity.ok("Cancel Shipping for order:" + orderID + " has been canceled");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in cancelling the shipping!");
    }

    @PostMapping("/cancel/compound/{username}/{compoundID}")
    public ResponseEntity<?> cancelCompoundOrderShipping(@PathVariable("compoundID") Integer compoundID, @PathVariable("username") String username) {
        Customer customer = com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository.GetCustomer(username);
        // check if the customer sent wrong data while creating the order
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is wrong, please enter correct data!");
        }
        boolean status = shippingService.cancelSimpleShipping(compoundID, COMPOUND_SHIPPING_FEES);
        if (status) {
//            com.OrdersandNotificationsManagement.demo.Service.OrderService.cancelCompoundOrder(compoundID);
            return ResponseEntity.ok("Cancel Shipping for order:" + compoundID + " has been canceled");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was something wrong in cancelling the shipping!");
    }

}
