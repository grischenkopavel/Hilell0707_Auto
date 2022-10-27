package lesson25;/*
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test_isSelected {
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
    void isSelectedCheckboxTest() {
        WebElement login = driver.findElement(By.xpath("//li[contains(@class, '--user')]"));
        login.click();

        WebElement rememberCheckboxInput = driver.findElement(By.id("remember_me"));
        
        WebElement rememberCheckbox = driver.findElement(By.xpath("//label[contains(@for, 'remember_me')]"));

        System.out.println(rememberCheckboxInput.isSelected());

        rememberCheckbox.click();

        System.out.println(rememberCheckboxInput.isSelected());
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }
}
