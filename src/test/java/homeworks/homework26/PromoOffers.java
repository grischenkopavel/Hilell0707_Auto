package homeworks.homework26;/*
Created by Pavel Gryshchenko on 17.10.2022
*/
/*
Открыть https://rozetka.com.ua/
Проверить, что в блоке Акционные предложения отображается 6 товаров
Сохранить цену первого товара
Перейти на продуктовую первого товара и проверить, что цена совпадает с сохраненной
Вернуться на главную страницу (проверка URL)
Проверить, что в блоке Акционные предложения отображается 6 товаров
 */
/*
Each check - separate method? Architecture?
one assert - one method?
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PromoOffers {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private final int EXPECTED_PROMO_GOODS_COUNT = 6;
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void PromoOffers() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(5);
        By promo = By.xpath("//rz-goods-sections/section[1]//child::li");
        wait.until(ExpectedConditions.presenceOfElementLocated(promo));
        List<WebElement> promoOffersElements = driver.findElements(promo);
        int countOfPromoGoods = promoOffersElements.size();
        Assert.assertEquals(countOfPromoGoods, EXPECTED_PROMO_GOODS_COUNT);

        WebElement firstPromoGoodPriceElement = driver.findElement(By.xpath("//rz-goods-sections/section[1]//child::li//child::span[@class='tile__price-value']"));
        String firstPromoGoodPrice = firstPromoGoodPriceElement.getAttribute("innerText").trim();

        WebElement firstPromoGoodPriceElementTile = driver.findElement(By.xpath("//rz-goods-sections/section[1]//child::li"));
        firstPromoGoodPriceElementTile.click();

        WebElement cardFirstPromoElement = wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class, 'product-prices__b')]"))));
        String cardFirstPromoElementPrice = cardFirstPromoElement.getText().trim().replaceAll("[^0-9?!\\.]", "");

        Assert.assertEquals(firstPromoGoodPrice, cardFirstPromoElementPrice, "Do not match");

        driver.get(ROZETKA_URL);
        Assert.assertEquals(driver.getCurrentUrl(), ROZETKA_URL, "URL don't match");

        By promoAfterGoBack = By.xpath("//rz-goods-sections/section[5]//child::li");
        wait.until(ExpectedConditions.elementToBeClickable(promoAfterGoBack));
        List<WebElement> promoOffersElementsAfterGoBack = driver.findElements(promoAfterGoBack);
        int countOfPromoGoodsAfterGoBack = promoOffersElementsAfterGoBack.size();
        Assert.assertEquals(countOfPromoGoodsAfterGoBack, EXPECTED_PROMO_GOODS_COUNT);
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
