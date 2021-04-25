package com.maybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class, scanBasePackages = "com.maybank")
@SpringBootApplication
public class MaybankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaybankApplication.class, args);
    }

}
