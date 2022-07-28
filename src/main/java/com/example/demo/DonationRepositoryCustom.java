package com.example.demo;

import java.util.List;

interface DonationRepositoryCustom {
    Total getTotal(long startDate, long endDate, String org_id);
    List<MonthlyTotals> getMonthlyTotals(long startDate, long endDate, String org_id);
    List<Donation> getDonations();
}
