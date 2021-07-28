package ru.page;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest4 extends BaseTest {

    @DisplayName("Проверка выборки телефонов")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Apple"})
    @Test
    public void yanMarketPhone(String phoneName){
        open("https://yandex.ru/", YandexMainPage.class).
                openService("Маркет").
                openCatalog("Электроника").
                openSection("Смартфоны").
                manufacturer(phoneName).
                show12Items().
                checkSampleProduct();
    }
}
