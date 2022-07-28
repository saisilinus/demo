package com.example.demo;

import java.util.List;

public class AllTotals {
    public List<MonthlyTotals> monthlyTotals;
    public Total total;

    public AllTotals(){}

    public AllTotals(List<MonthlyTotals> monthlyTotals, Total total) {
        this.monthlyTotals = monthlyTotals;
        this.total = total;
    }
}
