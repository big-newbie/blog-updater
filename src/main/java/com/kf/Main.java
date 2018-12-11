package com.kf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by keefe
 * 2018-12-11
 */
@SpringBootApplication(scanBasePackages = "com.kf")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}