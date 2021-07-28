package ru.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class YanMarketMainPage {

    private String selectorButtonCatalog = "//div[contains(@data-apiary-widget-name,'Entrypoint')]//button";
    private String selectorCategoryCatalog = "//li[@data-zone-name='category-link']";

    @Step("Открыть каталог и выбрать раздел")
    public YanMarketSection openCatalog(String catalName){
        $x(selectorButtonCatalog).click();
        $x(selectorCategoryCatalog).shouldBe(Condition.visible);
        $$x(selectorCategoryCatalog).stream().filter(x -> x.getText().equals(catalName))
                .findFirst().get().click();
        return Selenide.page(YanMarketSection.class);
    }
}
