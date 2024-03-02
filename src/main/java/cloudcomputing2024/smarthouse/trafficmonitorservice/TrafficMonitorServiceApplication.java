package cloudcomputing2024.smarthouse.trafficmonitorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrafficMonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficMonitorServiceApplication.class, args);
    }

}
