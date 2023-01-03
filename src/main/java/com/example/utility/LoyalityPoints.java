package com.example.utility;

public class LoyalityPoints
{
    public static int ratio = 10;    

    public static int calculate(double totalPrice)
    {
        return (int)(Math.round(totalPrice / 10));
    }
}
