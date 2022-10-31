package lesson26;/*
Created by Pavel Gryshchenko on 27.10.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Test_upload {
    private WebDriver driver;
    private final String TEST_URL = "https://the-internet.herokuapp.com/upload";

    @BeforeMethod
    void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TEST_URL);
    }

    @Test
    void uploadTest() throws InterruptedException {
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
        uploadElement.sendKeys("C:\\Users\\grisc\\IdeaProjects\\Ithillel\\Hilell0707_Auto\\rozetkaTest.txt");
        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        uploadBtn.click();
        TimeUnit.SECONDS.sleep(5);
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
