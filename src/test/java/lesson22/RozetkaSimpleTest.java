package lesson22;/*
Created by Pavel Gryshchenko on 06.10.2022
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class RozetkaSimpleTest {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private final String EXPECTED_TITLE = "Комп'ютер Apple Mac Studio M1 Ultra/48 ядер GPU/64GB/1TB";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    @Test
    void RozetkaTest()
    {
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys("Mac");

        WebElement btnSearch = driver.findElement(By.xpath("//button[contains(@class, \"button_color_green\")]"));
        btnSearch.click();

        WebElement firstProduct = wait.
                until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"goods-tile__title\"]")));

        String firstProductTitle = firstProduct.getText().trim();

        Assert.assertEquals(firstProductTitle, EXPECTED_TITLE, "title doe's not equal to Mac");
    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
