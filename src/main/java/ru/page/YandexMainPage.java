package ru.page;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexMainPage {

    @Step("Открываем сервис {nameService}")
    public YanMarketMainPage openService(String nameService){
        $(By.linkText(nameService)).click();
        switchTo().window(1);
        return Selenide.page(YanMarketMainPage.class);
    }
}
