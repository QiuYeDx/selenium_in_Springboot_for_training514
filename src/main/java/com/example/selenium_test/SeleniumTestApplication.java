package com.example.selenium_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import selenium_test.SeleniumTest;

@SpringBootApplication
public class SeleniumTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeleniumTestApplication.class, args);
        SeleniumTest seleniumTest = new SeleniumTest();
        seleniumTest.testAutomation();
    }

}
