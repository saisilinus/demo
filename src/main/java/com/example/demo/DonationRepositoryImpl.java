package com.example.demo;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DonationRepositoryImpl implements DonationRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    Log log = LogFactory.getLog(DonationRepositoryImpl.class);

    public Total getTotal(long startDate, long endDate, String org_id){
        Criteria criteria = new Criteria();
        if(endDate > 0){
            criteria.andOperator(
            Criteria.where("status").is("Completed"),
            Criteria.where("org_id").is(org_id),
            Criteria.where("timestamp").lte(endDate),
            Criteria.where("timestamp").gte(startDate));
        }else{
            criteria.andOperator(
            Criteria.where("status").is("Completed"),
            Criteria.where("org_id").is(org_id),
            Criteria.where("timestamp").gte(startDate));
        }
        MatchOperation filter = match(criteria);
        ProjectionOperation projectIdAndUsdAmount = project("_id", "usd_amount");
        GroupOperation groupAndSumDonations = group().sum("usd_amount").as("total");
        ProjectionOperation projectTotal = project("total");
        Aggregation aggregation = Aggregation.newAggregation(filter, projectIdAndUsdAmount, groupAndSumDonations, projectTotal);
            
            
            
        AggregationResults<Total> results = mongoTemplate.aggregate(aggregation, "donation", Total.class);
        // log.info(results.getMappedResults());
        if (!results.getMappedResults().isEmpty())
            return results.getMappedResults().get(0);
        else
            return null;
    }

    public List<MonthlyTotals> getMonthlyTotals(long startDate, long endDate, String org_id){
        Criteria criteria = new Criteria();
        if(endDate > 0){
            criteria.andOperator(
            Criteria.where("status").is("Completed"),
            Criteria.where("org_id").is(org_id),
            Criteria.where("timestamp").lte(endDate),
            Criteria.where("timestamp").gte(startDate));
        }else{
            criteria.andOperator(
            Criteria.where("status").is("Completed"),
            Criteria.where("org_id").is(org_id),
            Criteria.where("timestamp").gte(startDate));
        }
        MatchOperation filter = match(criteria);
        ProjectionOperation project = project("_id", "usd_amount").andExpression("month(toDate(timestamp))").as("month");
        GroupOperation groupByMonth = group("month").sum("usd_amount").as("total");
        ProjectionOperation projectMonthAndTotal = project("total").and("month").previousOperation();
        SortOperation sortByMonth = sort(Sort.by(Direction.ASC, "month"));

        Aggregation aggregation = Aggregation.newAggregation(filter, project, groupByMonth, projectMonthAndTotal, sortByMonth);
        AggregationResults<MonthlyTotals> results = mongoTemplate.aggregate(aggregation, "donation", MonthlyTotals.class);
        if (!results.getMappedResults().isEmpty())
            return results.getMappedResults();
        else
            return null;
    }

    public List<Donation> getDonations(){
        Query query = new Query();
        List<Donation> results = mongoTemplate.find(query, Donation.class);
        return results;
    }
}
