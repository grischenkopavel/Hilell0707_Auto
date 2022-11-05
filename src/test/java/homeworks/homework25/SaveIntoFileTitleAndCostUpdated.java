package homeworks.homework25;/*
Created by Pavel Gryshchenko on 17.10.2022
*/
/*
Перейти на https://rozetka.com.ua/
В сайд меню перейти в раздел Ноутбуки и компьютеры
Перейти в категорию Ноутбуки
На странице поисковой выдачи собрать все тайтлы(название товаров) и цены товаров. (60 штук)
Записать их в Map.
С помощью FileWriter записать данные с Map в файл rozetkaTest.txt (Пример ОЖ результат: Ноутбук Acer Nitro 5 AN515-57-54K7 (NH.QESEU.003) Shale Black - 32 999)
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

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveIntoFileTitleAndCostUpdated {
    private WebDriver driver;
    private final String ROZETKA_URL = "https://rozetka.com.ua/";
    private WebDriverWait wait;

    @BeforeTest
    void setUpTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ROZETKA_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void SaveIntoFileTitleAndCost() throws IOException {
        WebElement computersNotebooksLink = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='menu-categories__link']")));
        computersNotebooksLink.click();

        WebElement notebooksLink = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'cats__heading tile')]")));
        notebooksLink.click();


        By titles = By.xpath("//span[@class='goods-tile__title']");
        By prices = By.xpath("//span[contains(@class, 'goods-tile__price-v')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(titles));
        wait.until(ExpectedConditions.presenceOfElementLocated(prices));

        //TimeUnit.SECONDS.sleep(5);
        List<WebElement> titlesOfProducts = driver.findElements(titles);
        List<WebElement> priceOfProducts = driver.findElements(prices);

        Map<String, String> titlesAndCostsOfProducts = new HashMap<>();
        for (int i = 0; i < titlesOfProducts.size(); i++) {
            String title = titlesOfProducts.get(i).getAttribute("innerText").trim();
            String price = priceOfProducts.get(i).getAttribute("innerText").trim();
            titlesAndCostsOfProducts.put(title, price);
        }

        FileWriter fileWriter = new FileWriter("rozetkaTest.txt");
        for (Map.Entry<String, String> entry : titlesAndCostsOfProducts.entrySet()) {
            fileWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        fileWriter.close();
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }

}
