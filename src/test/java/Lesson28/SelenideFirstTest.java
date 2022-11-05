package Lesson28;/*
Created by Pavel Gryshchenko on 03.11.2022
*/

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class SelenideFirstTest {
    @BeforeTest
    void before() {
        Configuration.browserSize = "3840x2160";
        Configuration.timeout = 6000; // default 4000 ms
        open("https://rozetka.com.ua/");
    }

    @Test
    void firstSelenideTest() {
        $(By.name("search")).setValue("Mac");
        $(By.xpath("//button[contains(@class, 'button_color_green')]"))
                .shouldBe(visible)
                .shouldHave(text("Найти"), Duration.ofSeconds(5))
                .shouldNotHave((text("Alex")))
                .shouldNotHave(value("Поиск в Google"))
                .shouldHave(attribute("required"))
                .click();
        sleep(5000);
    }
}
