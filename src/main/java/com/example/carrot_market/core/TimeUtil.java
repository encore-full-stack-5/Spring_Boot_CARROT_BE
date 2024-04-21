package com.example.carrot_market.core;

public class TimeUtil {

    // make SQL Timestamp from current time
    public static java.sql.Timestamp getCurrentTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }
}
