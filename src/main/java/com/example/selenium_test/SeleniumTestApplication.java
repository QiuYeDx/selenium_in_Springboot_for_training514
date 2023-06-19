package com.example.selenium_test;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import selenium_questionnaire.SeleniumQuestionnaire;
import selenium_test.SeleniumTest;

@SpringBootApplication
public class SeleniumTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeleniumTestApplication.class, args);
        SeleniumQuestionnaire seleniumQuestionnaire = new SeleniumQuestionnaire();
        WebDriver driver =  seleniumQuestionnaire.initWebDriver("/Users/baizihan/Documents/学习空间/Java/selenium_test/src/main/resources/chromedriver");
        if(seleniumQuestionnaire.testLogin(driver) &&
                seleniumQuestionnaire.testCreateQuestionnaireStepA(driver)){
            System.out.println("测试流程完毕!");
        }
    }

}
