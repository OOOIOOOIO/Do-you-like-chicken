package com.sh.chicken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class ChickenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChickenApplication.class, args);
    }

}
