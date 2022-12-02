package homeworks.homework28.rozetka_page_object;/*
Created by Pavel Gryshchenko on 08.11.2022
*/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CategorySearchLogic {
    SelenideElement firstGoods = $(By.xpath("//span[contains(@class, 'goods-tile__title')]"));
    SelenideElement addToCardBtn = $(By.xpath("//button[contains(@class, 'goods-tile__buy-button')]"));
    SelenideElement cardBtnCounter = $(By.xpath("//span[contains(@class, 'counter')]"));

    String getFirstProductTitle() {
        firstGoods.shouldBe(Condition.visible);
        return this.firstGoods.text().trim();
    }

    String getGoodsInCardQty() {
        cardBtnCounter.shouldBe(Condition.visible);
        return this.cardBtnCounter.text().trim();
    }

    void addGoodsToCard() {
        addToCardBtn.shouldBe(Condition.visible);
        addToCardBtn.click();
    }

    CardPageLogic clickOnCardBtn() {
        this.cardBtnCounter.shouldBe(Condition.visible);
        this.cardBtnCounter.click();
        return page(CardPageLogic.class);
    }
}
