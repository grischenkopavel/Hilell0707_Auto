package lesson30;/*
Created by Pavel Gryshchenko on 13.11.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AshotDemo {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
    }

    @Test
    void ashot() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Screenshot screenshot = new AShot().takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "PNG", new File("screenshots/screen.png"));
    }

    @AfterTest
    void after() {
        driver.close();
    }
}
