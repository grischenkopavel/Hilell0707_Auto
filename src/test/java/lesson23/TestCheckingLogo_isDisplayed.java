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

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestCheckingLogo_isDisplayed {
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
    void RozetkaTest() {
        WebElement logo = driver.findElement(By.xpath("//a[@class='header__logo']"));
        Assert.assertTrue(logo.isDisplayed(), "Logo doesn't appear");
    }
    @Test 
    void RozetkaCheckLogo(){
        List<WebElement> logoList = driver.findElements(By.xpath("//a[@class='header__logo']"));
        if (logoList.size() >0){
            System.out.println("Logo appeared");
        }else {
            Assert.fail("Logo doesn't appear");
        }
    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
