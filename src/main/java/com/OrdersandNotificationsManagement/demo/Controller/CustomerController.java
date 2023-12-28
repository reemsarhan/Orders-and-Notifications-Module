package com.OrdersandNotificationsManagement.demo.Controller;


import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse;
import com.OrdersandNotificationsManagement.demo.Model.Response;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;
import com.OrdersandNotificationsManagement.demo.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Customer")

public class CustomerController
{


   CustomerService customerservice=new CustomerService();
    CustomersRepository rep=new CustomersRepository();
   LoggedInResponse logged_status;




@PostMapping("/createaccount")
public Response addCustomer(@RequestBody Customer c)
{
    boolean res = customerservice.addCustomer(c);
    Response response = new Response();
    if (!res)
    {
        response.setStatus(false);
        response.setMessage("Account Already Exists");
        return response;
    }
    response.setStatus(true);
    response.setMessage("Account created successfully");
    return response;
}


    @PostMapping("/Login")
    public Response Login(@RequestBody Customer c)
    {

        boolean res = customerservice.Login(c.GetUserName(),c.GetPassword());
        Response response = new Response();
        if (!res)
        {
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

    @GetMapping("/view")

    public ArrayList<Customer> view()
    {
        return rep.ViewRepo();
    }


}





