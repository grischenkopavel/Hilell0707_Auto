package lesson27.page_object;/*
Created by Pavel Gryshchenko on 31.10.2022
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class ProductPageLogic {
    By productTitle = By.xpath("//h1[@class='product__title']");
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPageLogic(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    String getTitleText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
        return driver.findElement(productTitle).getText().trim();
    }
}
