package homeworks.homework28.rozetka_page_object;/*
Created by Pavel Gryshchenko on 08.11.2022
*/
/*
Написать тест с использованием Selenide и PageObject
Открыть https://rozetka.com.ua/
Перейти в раздел «Компьютеры и ноутбуки»
Перейти в раздел « Ноутбуки»
Добавить первый товар в корзину
Проверить что в корзину добавился один товар
Перейти в корзину и проверить, что добавился правильный товар
 */

import com.codeborne.selenide.Configuration;
import lesson29.page_object_selenide.CardPageLogic;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class Main {
    private final HomePageLogic HOME_PAGE_LOGIC = new HomePageLogic();
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private final String EXPECTED_NUMBER_OF_GOODS = "1";
    org.testng.asserts.SoftAssert softAssert = new org.testng.asserts.SoftAssert();

    @BeforeTest
    void before() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000; // default 4000 ms
        open(ROZETKA_URL);
    }

    @Test
    void addToCardCheck() {

        HOME_PAGE_LOGIC
                .clickOnCategory(HOME_PAGE_LOGIC.compAndLaptopLink)
                .clickOnCategory(new CategoryPageLogic().laptopLink);

        CategorySearchLogic categorySearchLogic = new CategorySearchLogic();
        String firstGoodsTitleText = categorySearchLogic.getFirstProductTitle();

        categorySearchLogic.addGoodsToCard();
        String goodsInCardQty = categorySearchLogic.getGoodsInCardQty();
        softAssert.assertEquals(goodsInCardQty, EXPECTED_NUMBER_OF_GOODS);

        CardPageLogic cardPageLogic = categorySearchLogic.clickOnCardBtn();
        String goodsInCardText = cardPageLogic.getFirstProductTitle();

        softAssert.assertEquals(goodsInCardText, firstGoodsTitleText);
        softAssert.assertAll();
    }
}
