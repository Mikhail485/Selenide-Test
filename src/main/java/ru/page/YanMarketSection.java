package ru.page;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class YanMarketSection {

    private String selectorSections = "//ul[@data-autotest-id='subItems']//a";

    @Step("Открываем раздел {nameSection}")
    public YanMarketPageSearch openSection(String nameSection){
        $$x(selectorSections).stream().filter(x -> x.getText().equals(nameSection))
                .findFirst().get().click();
        return Selenide.page(YanMarketPageSearch.class);
    }
}
