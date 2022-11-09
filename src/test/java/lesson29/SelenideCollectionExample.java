package lesson29;/*
Created by Pavel Gryshchenko on 07.11.2022
*/

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideCollectionExample {
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    void before() {
        Configuration.browserSize = "2048x1080";
        Configuration.timeout = 6000; // default 4000 ms
        open(ROZETKA_URL);
    }

    @Test
    void example() {
        ElementsCollection sideBarMenu = $$(By.xpath("//a[@class = 'menu-categories__link']"));
        int sizeCollection = sideBarMenu.size();

        System.out.println(sizeCollection);

        String text = sideBarMenu.get(1).getText();
        System.out.println(text);

        ElementsCollection sideBarMenuWithFilter = sideBarMenu.filterBy(Condition.text(""));
        System.out.println(sideBarMenuWithFilter.size() + " sideBarMenuWithFilter size");

        sideBarMenu.shouldHave(CollectionCondition.size(16));
    }
}
