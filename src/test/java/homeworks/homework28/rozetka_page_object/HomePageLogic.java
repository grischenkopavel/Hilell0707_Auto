package homeworks.homework28.rozetka_page_object;/*
Created by Pavel Gryshchenko on 08.11.2022
*/

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class HomePageLogic {
    ElementsCollection sideBarMenu = $$(By.xpath("//a[@class = 'menu-categories__link']"));
    SelenideElement compAndLaptopLink = $(By.xpath("//ul[@class='menu-categories menu-categories_type_main']/li[1]/a"));

    HomePageLogic checkCategoryQty(int size) {
        sideBarMenu.shouldHave(CollectionCondition.size(size));
        return this;
    }

    CategoryPageLogic clickOnCategory(SelenideElement category) {
        category.shouldBe(Condition.visible);
        category.click();
        return page(CategoryPageLogic.class);
    }
}
