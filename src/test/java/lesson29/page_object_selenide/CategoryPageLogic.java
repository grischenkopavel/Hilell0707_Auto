package lesson29.page_object_selenide;/*
Created by Pavel Gryshchenko on 08.11.2022
*/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class CategoryPageLogic {
    SelenideElement laptopLink = $(By.xpath("//a[contains(@href, 'c80004')]"));

    SearchPageLogic clickOnCategory(SelenideElement category) {
        category.shouldBe(Condition.visible);
        category.click();
        return page(SearchPageLogic.class);
    }
}
