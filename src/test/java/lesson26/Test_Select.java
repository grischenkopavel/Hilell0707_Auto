package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Test_Select {
    private WebDriver driver;
    private final String TEST_URL = "https://the-internet.herokuapp.com/dropdown";

    @BeforeMethod
    void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TEST_URL);
    }

    @Test(invocationCount = 2)
    void selectTest() throws InterruptedException {
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropDown = new Select(dropdownElement);
        dropDown.selectByValue("1");
        TimeUnit.SECONDS.sleep(5);

        dropDown.selectByIndex(2);
        TimeUnit.SECONDS.sleep(5);
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
