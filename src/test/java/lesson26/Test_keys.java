package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test_keys {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeMethod
    void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    @Test
    void uploadTest() throws InterruptedException {
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(Keys.chord(Keys.SHIFT, "mac"));
        TimeUnit.SECONDS.sleep(2);

        WebElement btnSearch = driver.findElement(By.xpath("//button[contains(@class, 'button_color_green')]"));
        btnSearch.sendKeys(Keys.ENTER);

        TimeUnit.SECONDS.sleep(2);
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
