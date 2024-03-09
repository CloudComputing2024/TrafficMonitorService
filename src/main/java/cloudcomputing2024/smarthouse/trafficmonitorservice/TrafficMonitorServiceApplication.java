package cloudcomputing2024.smarthouse.trafficmonitorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableReactiveMongoRepositories
public class TrafficMonitorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrafficMonitorServiceApplication.class, args);
    }

}
