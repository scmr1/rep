package ru.yandex.search;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.WebDriverSettings;
import ru.yandex.qatools.allure.junit.AllureRunListener;

public class MainTest extends WebDriverSettings {

    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new AllureRunListener());
    }

    @Test
    public void mainTest() {

        BeforeSearch beforeSearch = PageFactory.initElements(driver, BeforeSearch.class);

        beforeSearch.open();  //  открытие сслыки

        beforeSearch.searchElement();  //  поиск по тексту


        AfterSearch afterSearch = PageFactory.initElements(driver, AfterSearch.class);

        afterSearch.phoneCheck();  //  проверка наличия телефона на странице

        afterSearch.risingPrice();  //  фильтр по цене

        afterSearch.pageSelector();  //  выбор вкладки (костыль, скорее всего)

        afterSearch.priceCatcher();  //  фиксация цены

        afterSearch.shopNameCatcher();  //  сверка магазина

    }

}
