package lesson30;/*
Created by Pavel Gryshchenko on 13.11.2022
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AshotRegression {
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

        BufferedImage expectedImg = ImageIO.read(new File("screenshots/screen.png"));
        BufferedImage actualImg = screenshot.getImage();
        ImageIO.write(screenshot.getImage(), "PNG", new File("screenshots/screen2.png"));

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff imageDiff = imageDiffer.makeDiff(expectedImg, actualImg);

        Assert.assertTrue(imageDiff.hasDiff(), "images are different");
    }

    @AfterTest
    void after() {
        driver.close();
    }
}
