package com.OrdersandNotificationsManagement.demo.Controller;


import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse;
import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {
    CustomerService customerservice = new CustomerService();
    CustomersRepository rep = new CustomersRepository();
    LoggedInResponse logged_status;
    /**
     * Adds a new customer to the system.
     *
     * @param c The customer to be added.
     * @return A Response object indicating the result of the operation.
     */
    @PostMapping("/create")
    public Response addCustomer(@RequestBody Customer c) {
        boolean res = customerservice.addCustomer(c);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Account Already Exists");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Account created successfully");
        return response;
    }
    /**
     * Logs in a customer with the provided username and password.
     *
     * @param c The customer with username and password for login.
     * @return A Response object indicating the result of the login operation.
     */
    @PostMapping("/login")
    public Response login(@RequestBody Customer c)
    {
        boolean res = customerservice.Login(c.GetUserName(), c.GetPassword());
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Wrong username or password");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Logged in successfully");
        LoggedInResponse.UpdateStatus(true);
        System.out.println(LoggedInResponse.GetStatus());
        return response;
    }

    /**
     * Retrieves a list of all customers in the system.
     *
     * @return An ArrayList of Customer objects representing all customers in the system.
     */
    @GetMapping("/view")

    public ArrayList<Customer> view() {
        return rep.ViewRepo();
    }


}





