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
import org.openqa.selenium.JavascriptExecutor;
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

public class PromoOffersUpdated {
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
        By promo = By.xpath("//*[@class='main-goods ng-star-inserted'][1]/rz-goods-section//li");
        wait.until(ExpectedConditions.presenceOfElementLocated(promo));
        List<WebElement> promoOffersElements = driver.findElements(promo);
        int countOfPromoGoods = promoOffersElements.size();
        Assert.assertEquals(countOfPromoGoods, EXPECTED_PROMO_GOODS_COUNT);

        WebElement firstPromoGoodPriceElement = driver.findElement(By.xpath("//*[@class='main-goods ng-star-inserted'][1]/rz-goods-section//li//span[@class='tile__price-value']"));
        String firstPromoGoodPrice = firstPromoGoodPriceElement.getText().trim();

        promoOffersElements.get(0).click();

        WebElement cardFirstPromoElement = wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@class, 'product-prices__big')]"))));
        String cardFirstPromoElementPrice = cardFirstPromoElement.getText().replace("₴", "").trim();

        Assert.assertEquals(firstPromoGoodPrice, cardFirstPromoElementPrice, "Do not match");

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), ROZETKA_URL, "URL don't match");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scroll(0,1000)", "");

        By promoAfterGoBack = By.xpath("//*[@class='main-goods ng-star-inserted'][5]//rz-goods-section");
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
