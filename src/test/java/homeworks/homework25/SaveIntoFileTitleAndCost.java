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
import java.util.concurrent.TimeUnit;

public class SaveIntoFileTitleAndCost {
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
    void SaveIntoFileTitleAndCost() throws InterruptedException, IOException {
        WebElement computersNotebooksLink = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[@class='menu-categories__link']")));
        computersNotebooksLink.click();

        WebElement notebooksLink = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//a[contains(@class, 'cats__heading tile')]")));
        notebooksLink.click();

        By wishButton = By.xpath("//button[contains(@class,'wish-button')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(wishButton));

        //TimeUnit.SECONDS.sleep(5);
        List<WebElement> titlesOfProducts = driver.findElements(By.xpath("//span[@class='goods-tile__title']"));
        List<WebElement> priceOfProducts = driver.findElements(By.xpath("//span[contains(@class, 'goods-tile__price-value')]"));

        Map<WebElement, WebElement> titlesAndCostsOfProducts = new HashMap<>();
        Iterator<WebElement> i1 = titlesOfProducts.listIterator();
        Iterator<WebElement> i2 = priceOfProducts.listIterator();
        while (i1.hasNext() || i2.hasNext()) {
            titlesAndCostsOfProducts.put(i1.next(), i2.next());
        }
//        printMap(titlesAndCostsOfProducts);
        FileWriter fileWriter = new FileWriter("rozetkaTest.txt");
        for (Map.Entry<WebElement, WebElement> webElementEntry : titlesAndCostsOfProducts.entrySet()) {
            fileWriter.write(webElementEntry.getKey().getText() + " " + webElementEntry.getValue().getText() + "\n");
        }
        fileWriter.close();
    }

    @AfterTest
    void afterTest() {
        driver.quit();
    }

    public void printMap(Map<WebElement, WebElement> mapToPrint) {
        for (Map.Entry<WebElement, WebElement> elementWebElementEntry : mapToPrint.entrySet()
        ) {
            System.out.println(elementWebElementEntry.getKey().getText() + " " + elementWebElementEntry.getValue().getText());
        }
    }
}
