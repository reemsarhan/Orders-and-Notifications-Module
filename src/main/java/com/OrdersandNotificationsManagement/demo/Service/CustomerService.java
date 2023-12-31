package com.OrdersandNotificationsManagement.demo.Service;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.LoggedInResponse;
import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;

public class CustomerService
{
    /**
     * Adds a new customer to the system.
     *
     * @param c The customer to be added.
     * @return true if the customer is added successfully; false if the customer already exists or an exception occurs.
     */
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

    /**
     * Performs customer login and updates the logged-in status.
     *
     * @param Username The username of the customer.
     * @param Password The password of the customer.
     * @return true if the login is successful; false otherwise.
     */
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
