package lesson24;/*
Created by Pavel Gryshchenko on 17.10.2022
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

public class Test_findElements {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private final int NUMBER_OF_GOODS = 60;
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
    void RozetkaTest() {
        WebElement computersNotebooksLink =  wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='menu-categories__link']")));
        computersNotebooksLink.click();

        WebElement notebooksLink =  wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'cats__heading tile')]")));
        notebooksLink.click();

        List<WebElement> titlesOfProducts = driver.findElements(By.xpath("//span[@class='goods-tile__title']"));
        int countOfGoods = titlesOfProducts.size();
        Assert.assertEquals(countOfGoods, NUMBER_OF_GOODS);

    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
