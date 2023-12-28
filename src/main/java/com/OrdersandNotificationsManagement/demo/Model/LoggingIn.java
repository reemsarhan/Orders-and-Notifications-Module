package com.OrdersandNotificationsManagement.demo.Model;

import com.OrdersandNotificationsManagement.demo.Repos.CustomersRepository;

public interface LoggingIn
{
    public void LogIn(String UserName, String Password, CustomersRepository RP);
}
