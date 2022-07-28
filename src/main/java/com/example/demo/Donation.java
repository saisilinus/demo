package com.example.demo;

public class Donation {
    public String id;
    public String org_id;
    public Integer usd_amount;
    public String status;
    public Long timestamp;

    public Donation() {}

    public Donation(String org_id, Integer usd_amount, String status, Long timestamp) {
        this.org_id = org_id;
        this.status = status;
        this.usd_amount = usd_amount;
        this.timestamp = timestamp;
    }
}
