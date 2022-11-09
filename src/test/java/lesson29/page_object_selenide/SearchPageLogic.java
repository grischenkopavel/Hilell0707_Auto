package lesson29.page_object_selenide;/*
Created by Pavel Gryshchenko on 08.11.2022
*/

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

class SearchPageLogic {

    ElementsCollection goodsTitles = $$(By.xpath("//span[@class='goods-tile__title']"));

    SearchPageLogic checkGoodsQty(int size) {
        goodsTitles.shouldHave(CollectionCondition.size(size));
        return this;
    }
}
