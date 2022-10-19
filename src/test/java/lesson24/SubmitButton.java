package lesson24;/*
Created by Pavel Gryshchenko on 17.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SubmitButton {
    private WebDriver driver;
    private final String GOOGLE_URL = "https://www.google.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    public void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(GOOGLE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void submitTest() {

        WebElement btnAcceptCookie = driver.findElement(By.id("L2AGLb"));
        btnAcceptCookie.click();

        WebElement inputSearch = driver.findElement(By.name("q"));
        inputSearch.sendKeys("QA");

        WebElement btnSearch = driver.findElement(By.name("btnK"));
        if (btnSearch.getAttribute("value").equals("Поиск в Google")) {
            btnSearch.submit();
        } else {
            Assert.fail();
        }
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
