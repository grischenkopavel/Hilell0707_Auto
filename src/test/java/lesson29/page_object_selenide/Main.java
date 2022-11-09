package lesson29.page_object_selenide;/*
Created by Pavel Gryshchenko on 07.11.2022
*/
/*
Перейти на https://rozetka.com.ua/
В сайд меню перейти в раздел Ноутбуки и компьютеры
Перейти в категорию Ноутбуки
Проверить что в поисковой выдаче на первой странице отобразилось 60 товаров
 */

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class Main {
    private final MainPageLogic MAIN_PAGE_LOGIC = new MainPageLogic();
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    void before() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000; // default 4000 ms
        open(ROZETKA_URL);
    }

    @Test
    void checkGoodsQtyOnSearchPage() {
        MAIN_PAGE_LOGIC
                .checkCategoryQty(16)
                .clickOnCategory(MAIN_PAGE_LOGIC.compAndLaptopLink)
                .clickOnCategory(new CategoryPageLogic().laptopLink)
                .checkGoodsQty(60);
    }
}
