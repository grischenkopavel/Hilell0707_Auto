package homeworks.homework24;/*
Created by Pavel Gryshchenko on 17.10.2022
*/
/*
Открыть https://rozetka.com.ua/
Перейти в раздел «Компьютеры и ноутбуки» //XPath need to be short, use contain()
Перейти в раздел « Ноутбуки» //XPath need to be short, use contain()
Добавить первый товар в корзину
Проверить что в корзину добавился один товар
Перейти в корзину и проверить, что добавился правильный товар

wait - no sense to use with each find???
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCard {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    void addToCardCheck(){
        WebElement computersNotebooksLink = driver.findElement(
                (By.xpath("//ul[contains(@class, 'menu-categories menu')]/li[1]/a")));
        computersNotebooksLink.click();

        WebElement notebooksLink =  wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@href, 'c80004')]")));
        notebooksLink.click();

        WebElement firstGoods = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[contains(@class, 'goods-tile__title')]")));
        String firstGoodsText = firstGoods.getAttribute("innerText").trim();

        WebElement btnAddToCard = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[contains(@class, 'goods-tile__buy-button')]")));
        btnAddToCard.click();

        WebElement btnCard = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//span[contains(@class, 'counter')]")));
        String cardGoodsNumber = btnCard.getAttribute("innerText").trim();
        btnCard.click();

        Assert.assertEquals(cardGoodsNumber, "1", "number of added goods doesn't match 1");

        WebElement card = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//div[contains(@class, 'cart-product__main')]//a")));
        String goodsInCardText = card.getAttribute("innerText").trim();

        Assert.assertEquals(firstGoodsText, goodsInCardText, "Goods added to card doesn't match with selected");
    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
