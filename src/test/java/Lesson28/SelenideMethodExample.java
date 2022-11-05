package Lesson28;/*
Created by Pavel Gryshchenko on 03.11.2022
*/

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideMethodExample {
    @BeforeTest
    void before() {
        Configuration.browserSize = "2048x1080";
        //Configuration.browserPosition = "1x1";
        Configuration.timeout = 6000; // default 4000 ms
        //Configuration.browser = "edge";
        open("https://rozetka.com.ua/");
    }

    @Test
    void firstTest() {
        SelenideElement findBtn = $(byXpath("//button[contains(@class, 'button_color_green')]"));
        String textFromFindBtn = findBtn.text();
        System.out.println(textFromFindBtn);

        String formActionAttrFromFindBtn = findBtn.attr("formAction");
        System.out.println(formActionAttrFromFindBtn);

        String backgroundFromFindBtn = findBtn.getCssValue("background-color");
        System.out.println(backgroundFromFindBtn);
    }
}
