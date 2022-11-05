package lesson27.page_object;/*
Created by Pavel Gryshchenko on 31.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class PageObjectTest {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void rozetkaTest() {
        new RozetkaMainPageLogic(driver, wait).
                typeTextToSearchInput("Mac").
                clickFindBtn();

        SearchPageLogic searchPageLogic = new SearchPageLogic(driver, wait);
        String titleFromSearchPage = searchPageLogic.firstProductTitleText();
        searchPageLogic.clickOnFirstProduct();

        String titleFromProductPage = new ProductPageLogic(driver, wait).getTitleText();

        Assert.assertEquals(titleFromSearchPage, titleFromProductPage, "titles mismatch");
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
