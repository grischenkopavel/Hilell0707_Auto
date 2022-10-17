package lesson23;/*
Created by Pavel Gryshchenko on 13.10.2022
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

public class TestCheckingTitleOnProductPage_GetAttribute {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    void RozetkaTest()
    {
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys("Mac");

        WebElement btnSearch = driver.findElement(By.xpath("//button[contains(@class, 'button_color_green')]"));
        btnSearch.click();

        WebElement firstProduct = wait.
                until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='goods-tile__title']")));

        String firstProductTitle = firstProduct.getText().trim();

        firstProduct.click();
        WebElement productPageTitle = driver.findElement(By.xpath("//h1[@class='product__title']"));
        String productPageTitleText = productPageTitle.getAttribute("innerText").trim();

        Assert.assertEquals(firstProductTitle, productPageTitleText, "not equals");

    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
