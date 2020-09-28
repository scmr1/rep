package ru.yandex.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AfterSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    public AfterSearch(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void phoneCheck() {

        try {
            driver.findElement(By.xpath("//a[@title=\"Смартфон Apple iPhone SE 32GB\"]"));
            System.out.println("Телефон в списке найден");
        } catch (Exception e) {
            System.out.println("Телефон не найден");
        }
    }

    public void risingPrice() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"_1_mt1KJox2\"]")));
        driver.findElement(By.xpath("//button[@data-autotest-id=\"dprice\"]")).click();

        //костыль для прогрузки страницы, типа тред.слип, но лучше

        By firstResult = By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[1]/div[4]/div[1]/h3/a/span");
        WebElement timeout = wait.until(ExpectedConditions.presenceOfElementLocated(firstResult));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.stalenessOf(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(firstResult)).click();
    }

    public void pageSelector() {

        Set<String> winSet = driver.getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String newTab = winList.get(winList.size() - 1);
        //driver.close(); // закроет первую вкладку
        driver.switchTo().window(newTab);
    }

    public void priceCatcher() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[6]/div/div[2]/div/div/div/div/div[2]/div/span/span[1]")));
        WebElement price = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[6]/div/div[2]/div/div/div/div/div[2]/div/span/span[1]"));
        String textInsidePrice = price.getText();
        System.out.println("Цена телефона " + textInsidePrice);
    }

    public void shopNameCatcher() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[5]/div/div/div/ul/li[3]/div/a")));
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[5]/div/div/div/ul/li[3]/div/a")).click();
        WebElement shop = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div[5]/div[2]/div[1]/div/div/div/div/div/div/div/div[3]/div/div/div/div"));
        String textInsideShop = shop.getText();

        String textFromElement = ("Покупка на Маркете");

        if (textInsideShop.equals(textFromElement))
            System.out.println("Магази в котором телефон - Яндекс Маркет");
        else
            System.out.println("Ты увидел магию ЯндексМаркета, и магазин превратился в Беру");
    }
}
