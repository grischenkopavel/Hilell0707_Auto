package lesson29;/*
Created by Pavel Gryshchenko on 07.11.2022
*/

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SelenideMethodsExample {
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    void before() {
        Configuration.browserSize = "2048x1080";
        Configuration.timeout = 6000; // default 4000 ms
        open(ROZETKA_URL);
    }

    @Test
    void example() {
        String currentUrl = url();
        System.out.println(currentUrl);

        String source = source();
        System.out.println(source);
    }
}
