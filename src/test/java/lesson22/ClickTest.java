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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

 class ClickTest {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    void setUpTest()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test (groups = "Broken")
     void googleClickTestImplicitlyWait() {
        //ImplicitlyWait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ROZETKA_URL);
        driver.findElement(By.xpath("//button[@class = 'header__button ng-star-inserted']")).click();


    }
    @Test
    void googleClickTest() {

        driver.get(ROZETKA_URL);
        //wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until (ExpectedConditions.elementToBeClickable(By.xpath("//button[@class = 'header__button ng-star-inserted']")) );

        element.click();


    }
    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
