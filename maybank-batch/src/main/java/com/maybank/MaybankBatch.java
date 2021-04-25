package com.maybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class, scanBasePackages = "com.maybank")
public class MaybankBatch {

    public static void main(String[] args) {
        SpringApplication.run(MaybankBatch.class, args);
    }

}
