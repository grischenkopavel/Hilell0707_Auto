package lesson29.page_object_selenide;/*
Created by Pavel Gryshchenko on 07.11.2022
*/

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class MainPageLogic {
    ElementsCollection sideBarMenu = $$(By.xpath("//a[@class = 'menu-categories__link']"));

    SelenideElement compAndLaptopLink = $(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]/a"));

    MainPageLogic checkCategoryQty(int size) {
        sideBarMenu.shouldHave(CollectionCondition.size(size));
        return this;
    }

    CategoryPageLogic clickOnCategory(SelenideElement category) {
        category.shouldBe(Condition.visible);
        category.click();
        return page(CategoryPageLogic.class);
    }
}
