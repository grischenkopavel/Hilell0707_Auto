package lesson29.page_object_selenide;/*
Created by Pavel Gryshchenko on 09.11.2022
*/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CardPageLogic {

    SelenideElement goodsInCardTile = $(By.xpath("//div[contains(@class, 'cart-product__main')]//a"));

    public String getFirstProductTitle() {
        goodsInCardTile.shouldBe(Condition.visible);
        return this.goodsInCardTile.text().trim();
    }
}
