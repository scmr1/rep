package ru.yandex.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeforeSearch {

    private WebDriver driver;
    private WebDriverWait wait;

    public BeforeSearch(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open() {
        driver.get("https://market.yandex.ru/");  //  поиск строки
    }

    public void searchElement() {
        WebElement searchField = driver.findElement(By.xpath("//input[@id=\"header-search\"]"));  //  поиск поля

        searchField.sendKeys("iphone SE");  //  заполнение поля

        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();  //  нажатие кнопки поиска
    }
}
