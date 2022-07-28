package com.example.demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	private DonationRepositoryCustom repository;
	private static final Log log = LogFactory.getLog(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run(String... args) throws Exception {
		System.out.println("It works");
		Total total = repository.getTotal(1641029820000L, 1648373820000L, "1");
		List<MonthlyTotals> monthlyTotals = repository.getMonthlyTotals(1641029820000L, 1648373820000L, "1");
		log.info("Total");
		log.info("-------------------------------");
		log.info(total);
		log.info("Monthly Totals");
		log.info("-------------------------------");
		log.info(monthlyTotals);
	}

}
