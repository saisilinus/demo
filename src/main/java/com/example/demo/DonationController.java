package com.example.demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {
    @Autowired
    private DonationRepositoryCustom customRepository;

    Log log = LogFactory.getLog(DonationController.class);

    @RequestMapping("/")
    public String index() {
        Total total = customRepository.getTotal(1641029820000L, 1648373820000L, "1");
	    List<MonthlyTotals> monthlyTotals = customRepository.getMonthlyTotals(1641029820000L, 1648373820000L, "1");
        log.info("Total");
		log.info("-------------------------------");
		log.info(total.total);
		log.info("Monthly Totals");
		log.info("-------------------------------");
        for (MonthlyTotals monthlyTotal: monthlyTotals) {
            log.info("Month");
            log.warn(monthlyTotal.month);
            log.info("Total");
            log.warn(monthlyTotal.total);
        }

        // return total;
        // repository.save(new Donation("1", 500, "Completed", 1643274256000L));
        // Donation donation1 = new Donation("1", 300, "Completed", 1643274256000L);
        // Donation donation2 = new Donation("1", 200, "Incomplete", 1642670037000L);
        // Donation donation3 = new Donation("1", 55, "Completed", 1643447637000L);
        // Donation donation4 = new Donation("1", 400, "Completed", 1641978837000L);
        // Donation donation5 = new Donation("1", 15, "Completed", 1643706837000L);
        // Donation donation6 = new Donation("1", 75, "Completed", 1645780437000L);
        // Donation donation7 = new Donation("1", 1000, "Completed", 1646212437000L);
        // Donation donation8 = new Donation("1", 3000, "Completed", 1646817237000L);
        // Donation donation9 = new Donation("1", 3, "Completed", 1648026837000L);
        // Donation donation10 = new Donation("1", 100, "Completed", 1650705237000L);
        // Donation donation11 = new Donation("1", 150, "Completed", 1650964437000L);
        // Donation donation12 = new Donation("1", 250, "Incomplete", 1648545237000L);

        // List<Donation> dons = Arrays.asList(donation1, donation2, donation3, donation4, donation5, donation6, donation7, donation8, donation9, donation10, donation11, donation12);
        // repository.insert(dons);

        return "You did it";
    }
}
