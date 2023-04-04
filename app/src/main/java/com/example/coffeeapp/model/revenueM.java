package com.example.coffeeapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class revenueM {
    private LocalDate date;
    private double revenue;

    public revenueM(LocalDate date, double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
