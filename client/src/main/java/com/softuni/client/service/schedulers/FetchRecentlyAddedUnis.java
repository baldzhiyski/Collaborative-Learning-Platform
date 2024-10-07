package com.softuni.client.service.schedulers;


import com.softuni.client.service.impl.SeedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FetchRecentlyAddedUnis {
    private static final Logger logger = LoggerFactory.getLogger(FetchRecentlyAddedUnis.class);

    private SeedServiceImpl service;

    public FetchRecentlyAddedUnis(SeedServiceImpl service) {
        this.service = service;
    }


    @Scheduled(cron = "0 0 0 * * MON")  // Runs every Monday at midnight
    public void addRecentlyAddedUniversitiesToTheDB(){
        logger.info("Scheduled task started: Fetching and adding recently added universities to the database.");
        this.service.updateUniversitiesInfo();

    }
}
