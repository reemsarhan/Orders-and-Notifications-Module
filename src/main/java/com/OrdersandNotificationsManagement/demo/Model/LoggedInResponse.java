package com.OrdersandNotificationsManagement.demo.Model;

public class LoggedInResponse
{

   static boolean Stat=false;

   public static void UpdateStatus(Boolean s)
    {
        Stat=s;
    }
    public static boolean GetStatus()
    {
        return Stat;
    }

}
