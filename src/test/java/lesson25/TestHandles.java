package lesson25;/*
Created by Pavel Gryshchenko on 24.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestHandles {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void Handles() throws InterruptedException {
        WebElement login = driver.findElement(By.xpath("//li[contains(@class, '--user')]"));
        login.click();

        WebElement reg = driver.findElement(By.xpath("//button[contains(@class, 'register-link')]"));
        reg.click();

        WebElement privatePolicy = driver.findElement(By.xpath("//a[contains(@class, 'button--link')]"));
        privatePolicy.click();

        String mainTab = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()
        ) {
            driver.switchTo().window(tab);
        }

        TimeUnit.SECONDS.sleep(5);

        driver.switchTo().window(mainTab);

        TimeUnit.SECONDS.sleep(5);
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
