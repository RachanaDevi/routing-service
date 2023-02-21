package com.example.routingservice;

import java.sql.Timestamp;

public class MainClass {

    public static void main(String[] args) {

        String dateTime = "2023-02-18 01:24:23";

        Timestamp timestamp = Timestamp.valueOf(dateTime);
        System.out.println(timestamp);
    }
}
