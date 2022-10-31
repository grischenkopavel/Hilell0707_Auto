package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test_frame {
    private WebDriver driver;
    private final String TEST_URL = "https://the-internet.herokuapp.com/iframe";

    @BeforeMethod
    void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TEST_URL);
    }

    @Test
    void uploadTest() throws InterruptedException {
        WebElement iFrameElement = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrameElement);

        WebElement inputIn_iFrame = driver.findElement(By.id("tinymce"));
        inputIn_iFrame.clear();
        inputIn_iFrame.sendKeys("Text in iFrame");
        TimeUnit.SECONDS.sleep(5);
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
