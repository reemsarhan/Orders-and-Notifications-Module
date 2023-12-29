package com.OrdersandNotificationsManagement.demo.Repos;

import com.OrdersandNotificationsManagement.demo.Model.Customer;
import com.OrdersandNotificationsManagement.demo.Model.Product;

import java.util.ArrayList;

public class CustomersRepository implements Repository
{
  public static ArrayList<Customer>Customers=new ArrayList<>();
    @Override
    public ArrayList<Customer> ViewRepo()
    {
      return Customers;
    }

   public static void AddCustomer(Customer c)
    {
        Customers.add(c);
    }

    public static void RemoveCustomer(Customer c)
    {
        Customers.remove(c);
    }

    public static boolean IsCustomerFound(String UserName,String  Password)
    {
        for (Customer cus:Customers)
        {
            if(cus.GetUserName().equals(UserName)&& cus.GetPassword().equals(Password))
                return true;
        }
        return false;
    }
}
