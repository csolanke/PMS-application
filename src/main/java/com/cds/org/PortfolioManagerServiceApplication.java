package com.cds.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.cds.org.computation", "com.cds.org.controller","com.cds.org.dto","com.cds.org.mapper"
,"com.cds.org.model","com.cds.org.persistence","com.cds.org.service"})
public class PortfolioManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioManagerServiceApplication.class, args);
	}

}
