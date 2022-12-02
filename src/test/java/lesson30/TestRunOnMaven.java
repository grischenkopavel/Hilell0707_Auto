package lesson30;/*
Created by Pavel Gryshchenko on 13.11.2022
*/

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestRunOnMaven {
    @Test
    void testOnMaven() {
        open("https://rozetka.com.ua/");
        System.out.println("Test Run from maven");
    }
}
