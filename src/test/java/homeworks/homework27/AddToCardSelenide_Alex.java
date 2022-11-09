package homeworks.homework27;/*
Created by Pavel Gryshchenko on 05.11.2022
*/
/*
Написать тест с использованием Selenide
Открыть https://rozetka.com.ua/
Перейти в раздел «Компьютеры и ноутбуки»
Перейти в раздел «Ноутбуки»
Добавить первый товар в корзину
Проверить, что в корзину добавился один товар
Перейти в корзину и проверить, что добавился правильный товар
 */

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddToCardSelenide_Alex {
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private final String EXPECTED_NUMBER_OF_GOODS = "1";

    @BeforeTest
    void before() {
        Configuration.browserSize = "2048x1080";
        Configuration.timeout = 6000; // default 4000 ms
        open(ROZETKA_URL);
    }

    @Test
    void addToCardCheck() {
        SelenideElement compAndLaptopLink = $(By.xpath("//ul[contains(@class, 'menu-categories menu')]/li[1]/a"));
        compAndLaptopLink.click();

        SelenideElement laptopLink = $(By.xpath("//a[contains(@href, 'c80004')]"));
        laptopLink.click();

        SelenideElement firstGoodsTitle = $(By.xpath("//span[contains(@class, 'goods-tile__title')]"));
        firstGoodsTitle.shouldBe(Condition.visible);
        String firstGoodsText = firstGoodsTitle.text().trim();

        SelenideElement firstProductAddBtn = $(By.xpath("//button[contains(@class, 'goods-tile__buy-button')]"));
        firstProductAddBtn.click();

        SelenideElement cardBtn = $(By.xpath("//span[contains(@class, 'counter')]"))
                .shouldBe(Condition.visible);
        String cardGoodsNumber = cardBtn.attr("innerText");

        org.testng.asserts.SoftAssert softAssert = new org.testng.asserts.SoftAssert();
        softAssert.assertEquals(cardGoodsNumber, EXPECTED_NUMBER_OF_GOODS, "Number of goods do not match");

        cardBtn.click();

        SelenideElement titleProductInCard = $(By.xpath("//div[contains(@class, 'cart-product__main')]//a"));
        String goodsInCardText = titleProductInCard.text().trim();
        softAssert.assertEquals(goodsInCardText, firstGoodsText);

        softAssert.assertAll();
    }
}
