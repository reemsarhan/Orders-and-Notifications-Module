package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;

public class CustomerService
{

    public Boolean addCustomer(Customer c)
    {
        try
        {
            if (CustomersRepository.IsCustomerFound(c.GetUserName(), c.GetPassword())) {
                System.out.println("Customer already exists.");
                return false;
            }
            else
            {
                CustomersRepository.AddCustomer(c);
            }
        } catch (Exception e) {
            System.out.println("Exception in addCustomer: " + e.getMessage());
            return false;
        }
        return true;
    }


    public Boolean Login(String Username, String Password)
    {
        try
        {
            if(CustomersRepository.IsCustomerFound(Username,Password))
            {
                System.out.println("Logged In Successfully");
                LoggedInResponse.UpdateStatus(true);
                return true;
            }
            else
            {
                System.out.println("Exception in Logging ");
                return false;
            }

        }
        catch (Exception e)
        {
            System.out.println("Exception in Logging in"+ e.getMessage());
            return false;
        }


    }

}
