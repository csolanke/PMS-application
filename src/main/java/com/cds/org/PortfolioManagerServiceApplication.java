package com.cds.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class,scanBasePackages={"com.cds.org.computation", "com.cds.org.controller","com.cds.org.dto","com.cds.org.mapper"
,"com.cds.org.model","com.cds.org.persistence","com.cds.org.service","com.cds.org.security","com.cds.org.advice",
"com.cds.org.exceptions","com.cds.org.hibernate.config"})
public class PortfolioManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioManagerServiceApplication.class, args);
	}

}
