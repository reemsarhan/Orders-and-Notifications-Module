package com.OrdersandNotificationsManagement.demo.Model;

import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;

public class CustomerLoggingIn implements LoggingIn
{
    @Override
    public void LogIn(String UserName, String Password, CustomersRepository CR)
    {
        if( CR.IsCustomerFound(UserName,Password))
        {
         LoggedInResponse.UpdateStatus(true);

        }
    }
}
