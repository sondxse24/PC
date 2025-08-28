package com.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pc")
public class LaptopApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaptopApplication.class, args);
    }

}
